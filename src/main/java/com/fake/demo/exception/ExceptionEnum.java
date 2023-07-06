package com.fake.demo.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ExceptionEnum implements ErrorInfoInterface{
    INPUT_ERROR("4000", "输入错误"),
    SERVICE_ERROR("4001", "服务器错误"),
    DATABASE_ERROR("4002", "数据库错误"),
    ID_NOT_FOUND("4003", "查无此ID"),
    PERMISSION_DENIED("5000", "用户名未登录"),
    PASSWORD_NOT_MATCH("5001", "用户名与密码不匹配");

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
