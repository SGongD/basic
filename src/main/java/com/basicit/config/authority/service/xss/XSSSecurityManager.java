package com.basicit.config.authority.service.xss;

import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import javax.servlet.FilterConfig;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author Crackers
 * @Description Security filtering configuration management class
 * @date Mar 24, 2017 7:45:22 PM
 */
@SuppressWarnings("all")
public class XSSSecurityManager {

    private static final Logger log = LoggerFactory.getLogger(XSSSecurityManager.class);

    /**
     * REGEX：Check Regular Expression
     */
    public static String REGEX;

    /**
     * special character match
     */
    private static Pattern XSS_PATTERN;

    /**
     * Special url matching rule map，<url,regex>
     */
    public static List<Map<String, Object>> checkUrlMatcherList = new ArrayList<Map<String, Object>>();

    /**
     * Constructor
     */
    private XSSSecurityManager() {
    }

    /**
     * Initialize
     *
     * @param config Configuration parameters
     */
    public static void init(FilterConfig config) {

        log.debug("XSSSecurityManager init(FilterConfig config) begin");
        // Initialize the filter configuration file

        log.debug("xss classpath={}", config.getInitParameter("securityconfig"));
        // Initialize security filter configuration
        try {
            URL xssPath = ResourceUtils.getURL(config.getInitParameter("securityconfig"));
            log.debug("xss URL={}", xssPath);
            if (initConfig(xssPath)) {
                // Generate matchers
                XSS_PATTERN = Pattern.compile(REGEX);
                if (log.isDebugEnabled()) {
                    for (Map matchMap : checkUrlMatcherList) {
                        log.debug("Special URL Filtering Matching Rules" + matchMap);
                    }
                }

            } else {
                log.debug("Failed to initialize XSS configuration!");
                throw new RuntimeException("Failed to initialize XSS configuration!");
            }
        } catch (DocumentException e) {
            log.debug("The security filtering configuration file xss security config.xml is loaded abnormally");
            e.printStackTrace();
        } catch (IOException e) {
            log.debug("The security filtering configuration file xss security config.xml is loaded abnormally");
            e.printStackTrace();
        }
        log.debug("XSSSecurityManager init(FilterConfig config) end");
    }

    /**
     * Read the security audit configuration file xss_security_config.xml to set the XSSSecurityConfig configuration information
     *
     * @param path Filter configuration file path
     * @return ture or false
     * @throws DocumentException
     */
    public static boolean initConfig(URL url) throws DocumentException {
        log.debug("XSSSecurityManager.initConfig(URL url) begin");
        Element superElement = new SAXReader().read(url).getRootElement();
        XSSSecurityConfig.IS_CHECK_HEADER = new Boolean(getEleValue(superElement, XSSSecurityConstants.IS_CHECK_HEADER));
        XSSSecurityConfig.IS_CHECK_PARAMETER = new Boolean(getEleValue(superElement, XSSSecurityConstants.IS_CHECK_PARAMETER));
        XSSSecurityConfig.IS_CHECK_URL = new Boolean(getEleValue(superElement, XSSSecurityConstants.IS_CHECK_URL));
        XSSSecurityConfig.IS_LOG = new Boolean(getEleValue(superElement, XSSSecurityConstants.IS_LOG));
        XSSSecurityConfig.IS_CHAIN = new Boolean(getEleValue(superElement, XSSSecurityConstants.IS_CHAIN));
        XSSSecurityConfig.REPLACE = new Boolean(getEleValue(superElement, XSSSecurityConstants.REPLACE));

        Element regexEle = superElement.element(XSSSecurityConstants.REGEX_LIST);
        Element checkUrlEles = superElement.element(XSSSecurityConstants.CHECK_URL_LIST);

        // Load special url filtering configuration
        if (checkUrlEles != null) {
            Iterator<Element> checkUrls = checkUrlEles.elementIterator();

            while (checkUrls.hasNext()) {
                Element checkUrlItem = checkUrls.next();

                Iterator<Element> urlItem = checkUrlItem.elementIterator();
                Map<String, Object> checkUrlMatch = new HashMap<String, Object>();
                String tempCheckUrlKey = "";
                StringBuffer tempCheckUrlRegValue = new StringBuffer("^");

                while (urlItem.hasNext()) {
                    Element element = urlItem.next();
                    if (XSSSecurityConstants.CHECK_URL_URL.equals(element.getName())) {
                        tempCheckUrlKey = element.getText();
                    } else if (XSSSecurityConstants.CHECK_URL_REGEX.equals(element.getName())) {
                        String tmp = element.getText();
                        tmp = tmp.replaceAll("\\\\\\\\", "\\\\");
                        tempCheckUrlRegValue.append(tmp);
                        tempCheckUrlRegValue.append("|");
                    }
                }

                String tempReg = tempCheckUrlRegValue.toString().substring(0, tempCheckUrlRegValue.length() - 1) + "$";
                if (!"".equals(tempCheckUrlKey)) {
                    if (!tempReg.startsWith("^")) {
                        tempReg = "^" + tempReg;
                    }
                    checkUrlMatch.put(tempCheckUrlKey, tempReg);
                    checkUrlMatcherList.add(checkUrlMatch);
                } else {
                    log.debug("Failed to load security filter configuration file: special url regular expression exception, url match is" + tempCheckUrlKey);
                    return false;
                }
            }

        } else {
            log.debug("None in the security filtering profile " + XSSSecurityConstants.CHECK_URL_LIST + " property");
            return false;
        }

        // Load generic url filtering configuration
        if (regexEle != null) {
            Iterator<Element> regexIt = regexEle.elementIterator();
            StringBuffer tempStr = new StringBuffer("^");
            // When the cdata tag of xml transmits data, it will add \ before \ by default, you need to replace \\ with \
            while (regexIt.hasNext()) {
                Element regex = (Element) regexIt.next();
                String tmp = regex.getText();
                tmp = tmp.replaceAll("\\\\\\\\", "\\\\");
                tempStr.append(tmp);
                tempStr.append("|");
            }
            if (tempStr.charAt(tempStr.length() - 1) == '|') {
                REGEX = tempStr.substring(0, tempStr.length() - 1) + "$";
                log.debug("Safe match rule" + REGEX);
            } else {
                // regex properties must be configured
                log.debug("Failed to load security filter configuration file: Regular expression exception " + tempStr.toString());
                return false;
            }
        } else {
            log.debug("None in the security filtering profile " + XSSSecurityConstants.REGEX_LIST + " 속성");
            return false;
        }

        log.debug("XSSSecurityManager.initConfig(String path) end");
        return true;

    }

    /**
     * Get the specified tag information from the target element, if the tag cannot be found, record the error log
     *
     * @param element target node
     * @param tagName make labels
     * @return
     */
    private static String getEleValue(Element element, String tagName) {
        if (isNullStr(element.elementText(tagName))) {
            log.debug("None in the security filtering profile " + XSSSecurityConstants.REGEX_LIST + " Property");
        }
        return element.elementText(tagName);
    }

    /**
     * Replace illegal characters - will replace the entire regular string
     *
     * @param text
     * @return
     */
    public static String securityReplace(String text) {
        if (isNullStr(text)) {
            return text;
        } else {
            return text.replaceAll(REGEX, XSSSecurityConstants.REPLACEMENT);
        }
    }

    /**
     * Whether the matched characters contain special characters
     *
     * @param text
     * @return
     */
    public static boolean matches(String text) {
        if (text == null) {
            return false;
        }
        return XSS_PATTERN.matcher(text).matches();
    }

    /**
     * release key information
     */
    public static void destroy() {
        log.debug("XSSSecurityManager.destroy() begin");
        XSS_PATTERN = null;
        REGEX = null;
        log.debug("XSSSecurityManager.destroy() end");
    }

    /**
     * To determine whether it is an empty string, it is recommended to put it in a tool class
     *
     * @param value
     * @return
     */
    public static boolean isNullStr(String value) {
        return value == null || value.trim().equals("");
    }
}
