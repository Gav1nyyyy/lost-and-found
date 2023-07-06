package com.fake.demo.interceptor;

import com.fake.demo.exception.LoginBaseException;
import com.fake.demo.exception.ExceptionEnum;
import com.fake.demo.utility.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class LostInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // show request information in console
        log.info("preHandle");
        log.info(request.getRequestURL().toString());
        log.info(request.getRequestURI());
        String token = request.getHeader("token");
        if(!TokenUtil.checkToken(token)){
            throw new LoginBaseException(ExceptionEnum.PERMISSION_DENIED);
        }
        return true;
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("afterCompletion");
    }
}
