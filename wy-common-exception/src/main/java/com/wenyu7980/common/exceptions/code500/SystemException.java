package com.wenyu7980.common.exceptions.code500;

import com.wenyu7980.common.exceptions.AbstractException;

/**
 *
 * @author wenyu
 */
public class SystemException extends AbstractException {
    public static final int CODE = 1;

    public SystemException(Throwable cause, String message, Object... args) {
        super(500, CODE, cause, message, args);
    }

    public SystemException(String message, Object... args) {
        super(500, CODE, message, args);
    }
}
