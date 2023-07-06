package com.fake.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "manual-sql")
@Component
@Data
public class DemoProperties {

    private String url;
    private String driver;
    private String username;
    private String password;
}
