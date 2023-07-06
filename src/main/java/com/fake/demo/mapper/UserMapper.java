package com.fake.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fake.demo.bean.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    User fetchByUsername(@Param("username") String username);
}
