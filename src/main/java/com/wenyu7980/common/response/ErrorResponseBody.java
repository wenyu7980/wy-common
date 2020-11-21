package com.wenyu7980.common.response;

/**
 *
 * @author wenyu
 */
public class ErrorResponseBody {
    private int code;
    private String message;

    public ErrorResponseBody(int code, Object data) {
        this.code = code;
    }

    public ErrorResponseBody(int code, String message) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }
}
