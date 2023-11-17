package com.fake.demo;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication()
@EnableScheduling
@MapperScan("com.fake.demo.mapper")
@NacosPropertySource(dataId = "test", autoRefreshed = true)
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
