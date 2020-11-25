package com.wenyu7980.common.exceptions.code404;

import com.wenyu7980.common.exceptions.AbstractException;

/**
 *
 * @author wenyu
 */
public class NotFoundException extends AbstractException {
    public NotFoundException(String message, Object... args) {
        super(404, 1, message, args);
    }
}
