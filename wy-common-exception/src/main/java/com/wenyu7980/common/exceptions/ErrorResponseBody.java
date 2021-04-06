package com.wenyu7980.common.exceptions;

/**
 *
 * @author wenyu
 */
public class ErrorResponseBody {
    private int code;
    private String message;

    public ErrorResponseBody() {
    }

    public ErrorResponseBody(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public void setCodeMessage(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
