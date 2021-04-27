package com.wenyu7980.common.exceptions;

import java.text.MessageFormat;

/**
 *
 * @author wenyu
 */
public abstract class AbstractException extends RuntimeException {
    private final int status;
    private final int code;

    public AbstractException(int status, int code, String message, Object... args) {
        super(MessageFormat.format(message, args));
        this.status = status;
        this.code = code;
    }

    public AbstractException(int status, int code, Throwable cause, String message, Object... args) {
        super(MessageFormat.format(message, args), cause);
        this.status = status;
        this.code = code;
    }

    public int getStatus() {
        return status;
    }

    public int getCode() {
        return code;
    }
}
