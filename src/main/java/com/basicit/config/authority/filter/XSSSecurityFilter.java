package com.basicit.config.authority.filter;

import com.basicit.config.authority.service.xss.XSSHttpRequestWrapper;
import com.basicit.config.authority.service.xss.XSSSecurityConfig;
import com.basicit.config.authority.service.xss.XSSSecurityConstants;
import com.basicit.config.authority.service.xss.XSSSecurityManager;
import com.alibaba.fastjson2.JSON;
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
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

/**
 * @author Crackers
 * @Description xss attack script filter
 * @date Mar 24, 2017 7:43:01 PM
 */
@WebFilter(urlPatterns = "/*", filterName = "XSSCheck", initParams = {@WebInitParam(name = "securityconfig", value = "classpath:conf/xss_security_config.xml")})
public class XSSSecurityFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(XSSSecurityFilter.class);

    /**
     * initialization operation
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        XSSSecurityManager.init(filterConfig);
    }

    /**
     * destroy operation
     */
    @Override
    public void destroy() {
        log.debug("XSSSecurityFilter destroy() begin");
        XSSSecurityManager.destroy();
        log.debug("XSSSecurityFilter destroy() end");
    }

    /**
     * Security Audit Read Configuration Information
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // Determine whether to use HTTP
        checkRequestResponse(request, response);

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // http information encapsulation class
        XSSHttpRequestWrapper xssRequest = new XSSHttpRequestWrapper(httpRequest);

        // Encapsulate the request information and perform verification work. If the verification fails (including illegal characters), log records and request interrupt processing are performed according to the configuration information.
        if (xssRequest.validateParameter(httpResponse)) {
            if (XSSSecurityConfig.IS_LOG) {
                StringBuilder paramStr = new StringBuilder();
                Map<String, String[]> submitParams = httpRequest.getParameterMap();
                Set<String> submitNames = submitParams.keySet();
                String[] submitValues;
                for (String submitName : submitNames) {
                    submitValues = submitParams.get(submitName);
                    Arrays.stream(submitValues).forEachOrdered(paramStr::append);
                }

                log.debug("XSS Security Filter RequestURL:{}", httpRequest.getRequestURL());
                log.debug("param:{}", paramStr);
                log.debug("XSS Security Filter RequestParameter:{}", JSON.toJSONString(httpRequest.getParameterMap()));
            }
            // 작업을 중단할지 여부
            if (XSSSecurityConfig.IS_CHAIN) {
                request.setAttribute("err", "The parameter you entered has illegal characters, please enter the correct parameter!");
                request.setAttribute("pageUrl", httpRequest.getRequestURI());
                request.getRequestDispatcher(request.getServletContext().getContextPath() + XSSSecurityConstants.FILTER_ERROR_PAGE).forward(request, response);
                return;
            }

        }
        chain.doFilter(request, response);
    }

    /**
     * Judging Request, Response type
     *
     * @param request  ServletRequest
     * @param response ServletResponse
     */
    private void checkRequestResponse(ServletRequest request, ServletResponse response) throws ServletException {
        if (!(request instanceof HttpServletRequest)) {
            throw new ServletException("Can only process HttpServletRequest");
        }

        if (!(response instanceof HttpServletResponse)) {
            throw new ServletException("Can only process HttpServletResponse");
        }
    }
}
