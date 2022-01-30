package org.worker.common.helper.exception;

public class JedisLocalException extends RuntimeException {

    public JedisLocalException(String message) {
        super(message);
    }
}
