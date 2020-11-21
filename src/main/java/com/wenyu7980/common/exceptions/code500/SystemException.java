package com.wenyu7980.common.exceptions.code500;

import com.wenyu7980.common.exceptions.AbstractException;
import org.springframework.http.HttpStatus;

/**
 *
 * @author wenyu
 */
public class SystemException extends AbstractException {
    public SystemException(int code, String message, Object... args) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, code, message, args);
    }
}
