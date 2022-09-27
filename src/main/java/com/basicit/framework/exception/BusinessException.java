package com.basicit.framework.exception;

/**
 * custom exception class
 *
 * @author Crackers
 * @date 2021/11/19 13:54
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 2248546206040115304L;
    /**
     * error code
     */
    private String code;

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public String getCode() {
        return this.code;
    }
}
