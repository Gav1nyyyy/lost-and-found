package com.fake.demo.interceptor;

import com.fake.demo.exception.LoginBaseException;
import com.fake.demo.exception.ExceptionEnum;
import com.fake.demo.service.impl.RedisServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
@RequiredArgsConstructor
public class LostInterceptor implements HandlerInterceptor {

    private final RedisServiceImpl redisService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // show request information in console
        log.info("preHandle URL is {}", request.getRequestURL().toString());
        String token = request.getHeader("token");
        if(!redisService.hasKey("Auth:Login:" + token)){
            throw new LoginBaseException(ExceptionEnum.PERMISSION_DENIED);
        }
        return true;
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("afterCompletion");
    }
}
