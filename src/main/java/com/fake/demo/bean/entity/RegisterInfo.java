package com.fake.demo.bean.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterInfo {
    private String token;
    private String code;
    private User user;
}
