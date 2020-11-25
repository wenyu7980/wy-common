package com.wenyu7980.common.exceptions.code500;

import com.wenyu7980.common.exceptions.AbstractException;

/**
 *
 * @author wenyu
 */
public class SystemException extends AbstractException {
    public SystemException(String message, Object... args) {
        super(500, 1, message, args);
    }
}
