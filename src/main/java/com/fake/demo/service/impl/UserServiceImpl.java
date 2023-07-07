package com.fake.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.fake.demo.bean.entity.User;
import com.fake.demo.exception.ExceptionEnum;
import com.fake.demo.exception.LoginBaseException;
import com.fake.demo.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl {

    private final UserMapper userMapper;
    public User fetchByUsername(User user){
        // using mybatis plus
//        LambdaQueryWrapper<User> userLambdaQueryWrapper = Wrappers.lambdaQuery();
//        userLambdaQueryWrapper.eq(User::getUsername, user.getUsername());
//        User result = userMapper.selectOne(userLambdaQueryWrapper);

        // using UserMapper
        User result = userMapper.fetchByUsername(user.getUsername());

        if(result == null){
            throw new LoginBaseException(ExceptionEnum.PASSWORD_NOT_MATCH);
        }
        return result;
    }
}
