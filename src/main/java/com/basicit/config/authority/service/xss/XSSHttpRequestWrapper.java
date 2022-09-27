package com.basicit.config.authority.service.xss;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * @author Crackers
 * @Description Request information encapsulation class, used to judge and process special characters in request requests
 * @date Mar 24, 2017 7:44:32 PM
 */
public class XSSHttpRequestWrapper extends HttpServletRequestWrapper {

    /**
     * encapsulate http request
     *
     * @param request HttpServletRequest
     */
    public XSSHttpRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getHeader(String name) {

        String value = super.getHeader(name);
        // If special character replacement is enabled, replace special characters
        if (XSSSecurityConfig.REPLACE) {
            value = XSSSecurityManager.securityReplace(value);
        }
        return value;
    }

    @Override
    public String getParameter(String name) {

        String value = super.getParameter(name);
        // If special character replacement is enabled, replace special characters
        if (XSSSecurityConfig.REPLACE) {
            value = XSSSecurityManager.securityReplace(value);
        }
        return value;
    }

    /**
     * If there is no illegal data, return false;
     *
     * @return true=check passed, false=check failed
     */
    private boolean checkHeader() {

        Enumeration<String> headerParams = this.getHeaderNames();
        while (headerParams.hasMoreElements()) {
            String headerName = headerParams.nextElement();
            String headerValue = this.getHeader(headerName);
            if (XSSSecurityManager.matches(headerValue)) {
                return true;
            }
        }
        return false;
    }

    /**
     * If there is no illegal data, return false;
     *
     * @return true=check passed, false=check failed
     */
    @SuppressWarnings("rawtypes")
    private boolean checkParameter() {

        // Special url verification, do not use general url regular verification
        if (XSSSecurityConfig.IS_CHECK_URL) {
            List<Map<String, Object>> checkUrlList = XSSSecurityManager.checkUrlMatcherList;
            for (Map matchMap : checkUrlList) {
                String requesturl = super.getRequestURL().toString();
                String matcherurl = matchMap.keySet().iterator().next().toString();
                //The request url matches the configured special url
                if (requesturl.contains(matcherurl)) {
                    return this.matches(matchMap.get(matcherurl).toString());
                }
            }
        }

        // Generic url for verification
        Map<String, String[]> submitParams = this.getParameterMap();
        Set<String> submitNames = submitParams.keySet();
        for (String submitName : submitNames) {
            String[] submitValues = submitParams.get(submitName);

            for (String submitValue : submitValues) {
                if (XSSSecurityManager.matches(submitValue)) {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * Whether there are special characters in the special url matching request parameters
     *
     * @param regex Regular
     * @return true=match succeeded, false=match failed
     */
    private boolean matches(String regex) {

        Pattern checkUrlPattern = Pattern.compile(regex);

        Map<String, String[]> submitParams = this.getParameterMap();
        Set<String> submitNames = submitParams.keySet();
        for (String submitName : submitNames) {
            String[] submitValues = submitParams.get(submitName);
            for (String submitValue : submitValues) {
                if (submitValue != null) {
                    if ("".equals(submitValue.trim()) || !checkUrlPattern.matcher(submitValue).matches()) {
                        continue;
                    }
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * If there is no illegal data, return false;
     * If there is illegal data, judge whether to jump to the error page according to the configuration information
     *
     * @param response HttpServletResponse
     * @return true=violating data, false=violating data
     */
    public boolean validateParameter(HttpServletResponse response) {

        // Start header verification and verify header information
        if (XSSSecurityConfig.IS_CHECK_HEADER) {
            if (this.checkHeader()) {
                return true;
            }
        }
        // Start parameter verification and verify the parameter information
        if (XSSSecurityConfig.IS_CHECK_PARAMETER) {
            return this.checkParameter();
        }

        return false;
    }

}
