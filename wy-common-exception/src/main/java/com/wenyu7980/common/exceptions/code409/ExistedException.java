package com.wenyu7980.common.exceptions.code409;

import com.wenyu7980.common.exceptions.AbstractException;

/**
 *
 * @author wenyu
 */
public class ExistedException extends AbstractException {
    public ExistedException(String message, Object... args) {
        super(409, 1, message, args);
    }
}
