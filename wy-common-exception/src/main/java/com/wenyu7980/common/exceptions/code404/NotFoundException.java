package com.wenyu7980.common.exceptions.code404;

import com.wenyu7980.common.exceptions.AbstractException;
import org.springframework.http.HttpStatus;

/**
 *
 * @author wenyu
 */
public class NotFoundException extends AbstractException {
    public NotFoundException(String message, Object... args) {
        super(HttpStatus.NOT_FOUND, 1, message, args);
    }
}
