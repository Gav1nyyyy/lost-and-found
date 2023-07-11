package com.fake.demo.bean.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user_list")
public class User {

    private String id;
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    private String email;
}
