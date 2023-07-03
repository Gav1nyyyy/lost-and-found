package com.fake.demo.bean;

import com.fake.demo.exception.ErrorInfoInterface;
import lombok.Data;

@Data
public class Result<T> {

    private String code;

    private String msg;

    private T data;

    public static <T> Result<T> success() {
        Result<T> result = new Result<>();
        result.setCode("0000");
        result.setMsg("success");
        return result;
    }

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode("0000");
        result.setMsg("success");
        result.setData(data);
        return result;
    }

    public static <T> Result<T> fail(String msg) {
        Result<T> result = new Result<>();
        result.setCode("1111");
        result.setMsg(msg);
        return result;
    }

    public static <T> Result<T> error(ErrorInfoInterface e){
        Result<T> result = new Result<>();
        result.setCode(e.getErrorCode());
        result.setMsg(e.getErrorMsg());
        return result;
    }
}
