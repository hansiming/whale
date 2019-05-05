package com.cszjo.whale.common.exception;

public enum ExceptionCode {

    SERVER_ALREADY_STARTED(400, "server already start!"),
    START_FAILED(500, "%s");

    private int code;
    private String message;

    ExceptionCode(int code, String message) {
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
