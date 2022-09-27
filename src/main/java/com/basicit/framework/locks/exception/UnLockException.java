package com.basicit.framework.locks.exception;

/**
 * <p>Unlock runtime exception</p>
 *
 * @author Crackers
 */
public class UnLockException extends RuntimeException {

    private static final long serialVersionUID = -2298723230022810352L;

    public UnLockException(String message) {
        super(message);
    }

    public UnLockException(String message, Throwable cause) {
        super(message, cause);
    }
}
