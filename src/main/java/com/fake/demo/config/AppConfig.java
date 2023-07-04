package com.fake.demo.config;

import com.fake.demo.service.impl.LostServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AppConfig {

  @Bean
  public LostServiceImpl lostService(DemoProperties demoProperties) {
    return new LostServiceImpl(
        demoProperties.getDriver(),
        demoProperties.getUrl(),
        demoProperties.getUsername(),
        demoProperties.getPassword());
  }
}
