package com.basicit.config.authority.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * @author Crackers
 * @Description xss parameter anti-injection
 * @date Mar 24, 2017 7:42:45 PM
 */
@SuppressWarnings("all")
@WebFilter(urlPatterns = "/*", filterName = "XSSCheck", initParams = {@WebInitParam(name = "securityconfig", value = "/*")})
public class XSSCheckFilter implements Filter {

    private static Logger log = LoggerFactory.getLogger(XSSCheckFilter.class);

    private FilterConfig config;
    // error jump destination
    private static String errorPath;
    // URLs that are not intercepted
    private static String[] excludePaths;
    // JS character keywords that need to be intercepted
    private static String[] safeless;

    /******************** xss attack anti-injection parameters begin ************************/
    // error jump destination
    public final static String XSS_ERROR_PATH = "/templates/common/error.ftl";
    // URLs that are not intercepted
    public final static String XSS_EXCLUDE_PATHS = "";
    // JS character keywords that need to be intercepted
    public final static String XSS_SAFELESS = "<script, </script, <iframe, </iframe, <frame, </frame, set-cookie, %3cscript, %3c/script, %3ciframe, %3c/iframe, %3cframe, %3c/frame, src=\"javascript:, <body, </body, %3cbody, %3c/body, <, >, </, />, %3c, %3e, %3c/, /%3e";

    /******************** xss attack anti-injection parameters end ************************/

    public void init(FilterConfig config) throws ServletException {
        this.config = config;
        try {
            errorPath = XSS_ERROR_PATH;
            String excludePath = XSS_EXCLUDE_PATHS;
            if (!"".equals(excludePath) && null != excludePath)
                excludePaths = excludePath.split(",");
            String safeles = XSS_SAFELESS;
            if (!"".equals(safeles) && null != safeles) {
                safeless = safeles.split(",");
                log.debug(safeless.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        Enumeration params = req.getParameterNames();
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        boolean isSafe = true;
        String requestUrl = request.getRequestURI();
        if (isSafe(requestUrl)) {
            requestUrl = requestUrl.substring(requestUrl.indexOf("/"));
            if (!excludeUrl(requestUrl)) {
                String cache = "";
                while (params.hasMoreElements()) {
                    cache = req.getParameter((String) params.nextElement());
                    if (!"".equals(cache) && null != cache) {
                        if (!isSafe(cache)) {
                            isSafe = false;
                            break;
                        }
                    }
                }
            }
        } else {
            isSafe = false;
        }
        if (!isSafe) {
            request.setAttribute("err", "The parameter you entered has illegal characters, please enter the correct parameter!");
            request.setAttribute("pageUrl", request.getRequestURI());
            request.getRequestDispatcher(errorPath).forward(request, response);
            return;
        }
        filterChain.doFilter(req, resp);
    }

    private static boolean isSafe(String str) {
        if (!"".equals(str) && null != str) {
            for (String s : safeless) {
                if (str.toLowerCase().contains(s))
                    return false;
            }
        }
        return true;
    }

    private boolean excludeUrl(String url) {
        if (excludePaths != null && excludePaths.length > 0) {
            for (String path : excludePaths) {
                if (url.toLowerCase().equals(path))
                    return true;
            }
        }
        return false;
    }

    public void destroy() {
    }

}
