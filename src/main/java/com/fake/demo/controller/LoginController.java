package com.fake.demo.controller;

import com.fake.demo.bean.Result;
import com.fake.demo.bean.entity.User;
import com.fake.demo.exception.ExceptionEnum;
import com.fake.demo.exception.LoginBaseException;
import com.fake.demo.service.impl.RedisServiceImpl;
import com.fake.demo.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/user")
@Slf4j
@RequiredArgsConstructor // inject lostService with a constructor
public class LoginController {

    private final UserServiceImpl userService;
    private final RedisServiceImpl redisService;

    @PostMapping(value = "/login")
    public Result<String> login(@Valid @RequestBody User user){
        if(!userService.fetchByUsername(user).getPassword().equals(user.getPassword())){
            throw new LoginBaseException(ExceptionEnum.PASSWORD_NOT_MATCH);
        }
        String token = UUID.randomUUID().toString();
        redisService.set("Auth:Login:" + token, user, 600);
        return Result.success(token);
    }

    @GetMapping(value = "/logout")
    public Result<String> logout(@RequestHeader String token){
        // TODO: remove token
        User user = (User) redisService.get("Auth:Login:" + token);
        redisService.delete("Auth:Login:" + token);
        return Result.success(user.getUsername() + " successfully logged out");
    }
}
