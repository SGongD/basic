package com.basicit.config.authority.filter;

import com.basicit.config.authority.service.xss.XSSSecurityConstants;
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
import java.io.IOException;
import java.util.Map;

/**
 * @author Crackers
 * @Description Prevent SQL Injection
 * @date Mar 24, 2017 7:42:29 PM
 */
@WebFilter(urlPatterns = "/*", filterName = "SQLInjection", initParams = {@WebInitParam(name = "regularExpression", value = "(?:')|(?:--)|(/\\*(?:.|[\\n\\r])*?\\*/)|" + "(\\b(select|update|and|or|delete|insert|trancate|char|into|substr|ascii|declare|exec|count|master|into|drop|execute)\\b)")})
public class SQLInjectionFilterServlet implements Filter {

    private static final Logger log = LoggerFactory.getLogger(SQLInjectionFilterServlet.class);

    private String regularExpression;

    public SQLInjectionFilterServlet() {
        log.debug("# init ");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        regularExpression = filterConfig.getInitParameter("regularExpression");
        log.info("######### regularExpression={}", regularExpression);
    }

    /**
     * If you need to enter "'", ";", "--" these characters, you can consider escaping these characters as html escape characters "'" escape character is: &39; ";" escape character is: &59;
     * the escape character is 45 45
     */
    @SuppressWarnings("rawtypes")
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String requestUrl = req.getRequestURL().toString();
        String contextPath = req.getContextPath();
        // get the url path stripped of context path
        requestUrl = requestUrl.substring(requestUrl.indexOf(contextPath) + contextPath.length());

        Map parametersMap = request.getParameterMap();
        for (Object o : parametersMap.entrySet()) {
            Map.Entry entry = (Map.Entry) o;
            String[] value = (String[]) entry.getValue();
            for (String msg : value) {
                if (null != msg && msg.matches(regularExpression)) {
                    log.info("#Suspected SQL injection attack! Parameter name: {}; Input information: {}", entry.getKey(), msg);
                    request.setAttribute("err", "The parameter you entered has illegal characters, please enter the correct parameter!");
                    request.setAttribute("pageUrl", req.getRequestURI());
                    request.getRequestDispatcher(request.getServletContext().getContextPath() + XSSSecurityConstants.FILTER_ERROR_PAGE).forward(request, response);
                    return;
                }
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        log.debug("# destroy ");
    }

}
