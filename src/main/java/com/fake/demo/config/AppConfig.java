package com.fake.demo.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AppConfig {
//    @NacosValue(value = "${server.port}", autoRefreshed = true)
//    private int port;
//
//    @Bean
//    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer() {
//        return factory -> factory.setPort(port);
//    }
}
