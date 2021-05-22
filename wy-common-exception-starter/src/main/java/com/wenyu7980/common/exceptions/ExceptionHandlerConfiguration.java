package com.wenyu7980.common.exceptions;

import com.wenyu7980.common.exceptions.code500.SystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

/**
 *
 * @author wenyu
 */

@RestControllerAdvice
public class ExceptionHandlerConfiguration {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlerConfiguration.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseBody> handler(Throwable e) {
        if (e instanceof AbstractException) {
            AbstractException exception = (AbstractException) e;
            if (exception instanceof SystemException) {
                LOGGER.error("开发异常", e);
            } else {
                LOGGER.debug("异常", e);
            }
            return new ResponseEntity<>(new ErrorResponseBody(exception.getCode(), exception.getMessage()),
              HttpStatus.resolve(exception.getStatus()));
        }
        if (e instanceof MethodArgumentNotValidException) {
            BindingResult result = ((MethodArgumentNotValidException) e).getBindingResult();
            String message = result.getFieldErrors().stream()
              .map(error -> error.getField() + error.getDefaultMessage() + ",值:" + error.getRejectedValue())
              .collect(Collectors.joining(","));
            return new ResponseEntity<>(new ErrorResponseBody(400, message), HttpStatus.BAD_REQUEST);
        }
        if (e instanceof HttpMessageNotReadableException) {
            return new ResponseEntity<>(new ErrorResponseBody(400, "缺少数据体"), HttpStatus.BAD_REQUEST);
        }
        if (e.getCause() != null) {
            return handler(e.getCause());
        }
        LOGGER.error("系统错误", e);
        return new ResponseEntity<>(new ErrorResponseBody(500, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
