package com.fake.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fake.demo.bean.entity.Lost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LostMapper extends BaseMapper<Lost> {
    void create(@Param("lost") Lost lost);
    int update(@Param("lost") Lost lost);
    int remove(@Param("nameID") String nameID);
    Lost fetchById(@Param("nameID") String nameID);
    List<Lost> fetchByPage(@Param("size") int size, @Param("offset") int offset);
}
