package com.fake.demo.exception;

import lombok.Data;

@Data
public class LoginBaseException extends RuntimeException {

    private ExceptionEnum exceptionEnum;

    public LoginBaseException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getErrorMsg());
        this.exceptionEnum = exceptionEnum;
    }

    public LoginBaseException(String message, ExceptionEnum exceptionEnum) {
        super(message);
        this.exceptionEnum = exceptionEnum;
    }
}
