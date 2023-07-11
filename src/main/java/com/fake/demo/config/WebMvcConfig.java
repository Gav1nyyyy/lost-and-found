package com.fake.demo.config;

import com.fake.demo.interceptor.LostInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    private LostInterceptor lostInterceptor;
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(lostInterceptor).
                addPathPatterns("/**").
                excludePathPatterns("/user/login").
                excludePathPatterns("/user/signin").
                excludePathPatterns("/user/verify");
    }
}
