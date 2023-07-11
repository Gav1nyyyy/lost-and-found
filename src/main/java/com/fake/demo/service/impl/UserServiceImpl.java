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

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl {

    private final UserMapper userMapper;

    public void create(User user){
        user.setId(UUID.randomUUID().toString());
        userMapper.insert(user);
    }

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

    public long countUsername(User user){
        LambdaQueryWrapper<User> userLambdaQueryWrapper = Wrappers.lambdaQuery();
        userLambdaQueryWrapper.eq(User::getUsername, user.getUsername());
        return userMapper.selectCount(userLambdaQueryWrapper);
    }

    public long countEmail(User user){
        LambdaQueryWrapper<User> userLambdaQueryWrapper = Wrappers.lambdaQuery();
        userLambdaQueryWrapper.eq(User::getEmail, user.getEmail());
        return userMapper.selectCount(userLambdaQueryWrapper);
    }

    public boolean contains(User user){
        return countUsername(user) > 0 && countEmail(user) > 0;
    }

}
