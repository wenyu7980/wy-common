package com.wenyu7980.common.exceptions;

import org.springframework.http.HttpStatus;

import java.text.MessageFormat;

/**
 *
 * @author wenyu
 */
public abstract class AbstractException extends RuntimeException {
    private final HttpStatus status;
    private final int code;

    public AbstractException(HttpStatus status, int code, String message, Object... args) {
        super(MessageFormat.format(message, args));
        this.status = status;
        this.code = code;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public int getCode() {
        return code;
    }
}
