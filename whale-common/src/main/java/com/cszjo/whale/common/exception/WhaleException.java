package com.cszjo.whale.common.exception;

public class WhaleException extends RuntimeException {

    public WhaleException(ExceptionCode code) {
        super(code.getMessage());
    }

    public WhaleException(ExceptionCode code, Object... format) {
        super(String.format(code.getMessage(), format));
    }
}
