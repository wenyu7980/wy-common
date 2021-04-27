package com.wenyu7980.common.exceptions.code400;

import com.wenyu7980.common.exceptions.AbstractException;

/**
 *
 * @author wenyu
 */
public class BadBodyException extends AbstractException {
    public static final int CODE = 1;

    public BadBodyException(String message, Object... args) {
        super(400, CODE, message, args);
    }
}
