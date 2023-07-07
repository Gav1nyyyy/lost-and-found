package com.fake.demo.utility;

import com.fake.demo.bean.entity.User;
import com.fake.demo.exception.ExceptionEnum;
import com.fake.demo.exception.LoginBaseException;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TokenUtil {

    private static final Map<String, User> tokenMap = new HashMap<>();

    public static String generateToken(User user){
        String token = UUID.randomUUID().toString();
        tokenMap.put(token, user);
        return token;
    }

    public static User getUserByToken(String token){
        return tokenMap.get(token);
    }

    public static boolean checkToken(String token){
        return tokenMap.containsKey(token);
    }

    public static void removeToken(String token){
        if(!checkToken(token)){
            throw new LoginBaseException(ExceptionEnum.PASSWORD_NOT_MATCH);
        }
        tokenMap.remove(token);
    }
}
