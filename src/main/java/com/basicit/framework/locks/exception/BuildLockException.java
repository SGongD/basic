package com.basicit.framework.locks.exception;

/**
 * <p>Create lock runtime exception</p>
 *
 * @author Crackers
 */
public class BuildLockException extends RuntimeException {

    private static final long serialVersionUID = 3875257035681233457L;

    public BuildLockException(String message) {
        super(message);
    }

    public BuildLockException(String message, Throwable cause) {
        super(message, cause);
    }
}
