package com.wenyu7980.common.exceptions.code403;

import com.wenyu7980.common.exceptions.AbstractException;

/**
 *
 * @author wenyu
 */
public class LoginFailException extends AbstractException {
    public static final int CODE = 1;

    public LoginFailException(String message, Object... args) {
        super(403, CODE, message, args);
    }
}
