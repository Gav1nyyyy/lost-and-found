package com.fake.demo.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@RequiredArgsConstructor
@Service
public class RedisServiceImpl {

    private final RedisTemplate<String, Object> redisTemplate;

    public void set(String key, Object object){
        redisTemplate.opsForValue().set(key, object);
    }

    public void set(String key, Object object, long time){
        if(time > 0){
            redisTemplate.opsForValue().set(key, object, time, TimeUnit.SECONDS);
        }
        else {
            redisTemplate.opsForValue().set(key, object);
        }
    }

    public Object get(String key){
        if(key == null){
            return null;
        }
        return redisTemplate.opsForValue().get(key);
    }

    public boolean hasKey(String key){
        return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    }

    public void delete(String key){
        redisTemplate.delete(key);
    }
}
