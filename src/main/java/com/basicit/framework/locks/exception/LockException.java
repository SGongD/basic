package com.basicit.framework.locks.exception;

/**
 * <p>Get lock runtime exception</p>
 *
 * @author Crackers
 */
public class LockException extends RuntimeException {

    private static final long serialVersionUID = -1881390443685916800L;

    public LockException(String message) {
        super(message);
    }

    public LockException(String message, Throwable cause) {
        super(message, cause);
    }
}
