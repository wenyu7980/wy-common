package com.wenyu7980.common.exceptions.code403;

import com.wenyu7980.common.exceptions.AbstractException;

/**
 * token失效
 * @author wenyu
 */
public class TokenExpiredException extends AbstractException {

    public static final int CODE = 2;

    public TokenExpiredException(String message, Object... args) {
        super(401, CODE, message, args);
    }
}
