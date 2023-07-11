package com.fake.demo.controller;

import com.fake.demo.bean.Result;
import com.fake.demo.bean.entity.RegisterInfo;
import com.fake.demo.bean.entity.User;
import com.fake.demo.exception.ExceptionEnum;
import com.fake.demo.exception.LoginBaseException;
import com.fake.demo.service.impl.EmailServiceImpl;
import com.fake.demo.service.impl.RedisServiceImpl;
import com.fake.demo.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Random;
import java.util.UUID;

@RestController
@RequestMapping("/user")
@Slf4j
@RequiredArgsConstructor // inject lostService with a constructor
public class UserController {

    private final UserServiceImpl userService;
    private final RedisServiceImpl redisService;
    private final EmailServiceImpl emailService;

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
        User user = (User) redisService.get("Auth:Login:" + token);
        redisService.delete("Auth:Login:" + token);
        return Result.success(user.getUsername() + " successfully logged out");
    }

    @PostMapping(value = "/signin")
    public Result<String> create(@Valid @RequestBody User user){
        if(userService.contains(user)){
            throw new LoginBaseException(ExceptionEnum.DUPLICATE_USERNAME);
        }
        Random random = new Random();
        String code = String.format("%06d", random.nextInt(999999));
        emailService.sendMail(user.getEmail(),
                "Lost and Found Creating Account",
                "Your verification code is: " + code);
        String tempToken = UUID.randomUUID().toString();
        redisService.set("Auth:Signin:" + tempToken, code, 180);
        return Result.success(tempToken);
    }

    @PostMapping(value = "/verify")
    private Result<String> verify(@RequestBody RegisterInfo registerInfo){
        Object o = redisService.get("Auth:Signin:" + registerInfo.getToken());
        if(o != null && o.equals(registerInfo.getCode())){
            userService.create(registerInfo.getUser());
            redisService.delete("Auth:Signin:" + registerInfo.getToken());
            return Result.success("Account created successfully");
        } else{
            throw new LoginBaseException(ExceptionEnum.VERIFY_FAILED);
        }
    }
}
