package com.fake.demo.handler;

import com.fake.demo.bean.Result;
import com.fake.demo.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;


@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public Result<String> exceptionHandler(MethodArgumentNotValidException e){
        log.error("input error occurs", e);
        return Result.error(ExceptionEnum.INPUT_ERROR);
    }

    @ExceptionHandler(value = LoginBaseException.class)
    @ResponseBody
    public Result<String> exceptionHandler(LoginBaseException e){
        log.error(e.getExceptionEnum().getErrorMsg(), e);
        return Result.error(e.getExceptionEnum());
    }

    @ExceptionHandler(value =
            LostBaseException.class)
    @ResponseBody
    public Result<String> exceptionHandler(LostBaseException e){
        log.error(e.getExceptionEnum().getErrorMsg(), e);
        return Result.error(e.getExceptionEnum());
    }

    @ExceptionHandler(value = SQLException.class)
    @ResponseBody
    public Result<String> exceptionHandler(SQLException e){
        log.error("database error occurs", e);
        return Result.error(ExceptionEnum.DATABASE_ERROR);
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result<String> exceptionHandler(Exception e){
        log.error("unknown error occurs", e);
        return Result.error(ExceptionEnum.SERVICE_ERROR);
    }
}
