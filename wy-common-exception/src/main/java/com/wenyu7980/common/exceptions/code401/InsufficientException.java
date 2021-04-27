package com.wenyu7980.common.exceptions.code401;

import com.wenyu7980.common.exceptions.AbstractException;

/**
 *
 * @author wenyu
 */
public class InsufficientException extends AbstractException {
    public static final int CODE = 1;

    public InsufficientException(String message, Object... args) {
        super(401, CODE, message, args);
    }
}
