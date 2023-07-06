package com.fake.demo.exception;

import lombok.Data;

@Data
public class LostBaseException extends RuntimeException{
    private ExceptionEnum exceptionEnum;

    public LostBaseException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getErrorMsg());
        this.exceptionEnum = exceptionEnum;
    }

    public LostBaseException(String message, ExceptionEnum exceptionEnum) {
        super(message);
        this.exceptionEnum = exceptionEnum;
    }
}
