package com.fake.demo.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ExceptionEnum implements ErrorInfoInterface{
    INPUT_ERROR("4000", "Input Error"),
    SERVICE_ERROR("4001", "Server Error"),
    DATABASE_ERROR("4002", "Database Error"),
    ID_NOT_FOUND("4003", "ID Not Found"),
    PERMISSION_DENIED("5000", "Username Is Not Logged In"),
    PASSWORD_NOT_MATCH("5001", "Username Does Not Match the Password"),
    VERIFY_FAILED("5002", "Verification Failed"),
    DUPLICATE_USERNAME("5003", "Username or Email Already Exists");

    private final String errorCode;
    private final String errorMsg;

    @Override
    public String getErrorCode() {
        return this.errorCode;
    }

    @Override
    public String getErrorMsg() {
        return this.errorMsg;
    }
}
