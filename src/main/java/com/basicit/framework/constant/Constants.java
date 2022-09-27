package com.basicit.framework.constant;

/**
 * @author Crackers
 * @Description constant class
 * @date Apr 12, 2017 9:42:34 AM
 */
public class Constants {

    /**
     * When paging, only 10 items are displayed per page
     */
    public static final Integer PAGE_SIZE = 10;

    /**
     *Status, 1=valid, 0=invalid
     */
    public static final Integer STATUS_VALID = 1;
    public static final Integer STATUS_INVALID = 0;

    /**
     * session & session key
     */
    public static final String PERMISSION_SESSION = "permission_session";
    public static final String SESSION_KEY = "session_key";

    /**
     * url & roleName
     */
    public static final String ROLE_CODE = "role_code";
    public static final String PERMISSION_URL = "permission_url";

    public static final String ROLE_BOSS_CODE = "boss_role";
    /**
     * administrator
     */
    public static final String ROLE_MANAGER_CODE = "manager_role";
    /**
     * general user
     */
    public static final String COMMON_ROLE_CODE = "common_role";

    public static final String ROLE_BOSS_NAME = "General manager";
    public static final String ROLE_MANAGER_NAME = "administrator";
    public static final String ROLE_COMMON_NAME = "general user";
}
