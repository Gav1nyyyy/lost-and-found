package com.fake.demo.controller;

import com.fake.demo.bean.Result;
import com.fake.demo.bean.entity.User;
import com.fake.demo.exception.ExceptionEnum;
import com.fake.demo.exception.LoginBaseException;
import com.fake.demo.service.impl.UserServiceImpl;
import com.fake.demo.utility.TokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@Slf4j
@RequiredArgsConstructor // inject lostService with a constructor
public class LoginController {

    private final UserServiceImpl userService;

    @PostMapping(value = "/login")
    public Result<String> login(@Valid @RequestBody User user){
        if(!userService.fetchByUsername(user).getPassword().equals(user.getPassword())){
            throw new LoginBaseException(ExceptionEnum.PASSWORD_NOT_MATCH);
        }
        String token = TokenUtil.generateToken(user);
        return Result.success("token: " + token);
    }

    @GetMapping(value = "/logout")
    public Result<String> logout(@RequestHeader String token){
        // TODO: remove token
        User user = TokenUtil.getUserByToken(token);
        TokenUtil.removeToken(token);
        return Result.success(user.getUsername() + " successfully logged out");
    }
}
