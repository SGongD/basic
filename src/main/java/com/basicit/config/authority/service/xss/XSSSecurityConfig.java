package com.basicit.config.authority.service.xss;

/**
 * @author Crackers
 * @Description Security filtering configuration information class
 * @date Mar 24, 2017 7:44:47 PM
 */
public class XSSSecurityConfig {

    /**
     * CHECK_HEADER：Whether to enable header verification
     */
    public static boolean IS_CHECK_HEADER;

    /**
     * CHECK_PARAMETER：Whether to enable parameter verification
     */
    public static boolean IS_CHECK_PARAMETER;

    /**
     * CHECK_URL,Whether to enable checking for special urls
     */
    public static boolean IS_CHECK_URL;

    /**
     * IS_LOG：whether to log
     */
    public static boolean IS_LOG;

    /**
     * IS_LOG：Whether to interrupt the operation
     */
    public static boolean IS_CHAIN;

    /**
     * REPLACE：Whether to enable replacement
     */
    public static boolean REPLACE;


}
