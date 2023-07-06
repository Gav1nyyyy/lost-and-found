package com.fake.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.fake.demo.bean.entity.Lost;
import com.fake.demo.mapper.LostMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageSerializable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor // inject lostService with a constructor
@Service
public class LostServiceImpl {

    private final LostMapper lostMapper;

    public void create(Lost lost) {
        lost.setNameID(UUID.randomUUID().toString());
        lost.setFoundItem(0);
        lostMapper.insert(lost);
    }

    public int update(Lost lost) {
        return lostMapper.updateById(lost);
    }

    public void updateStatus(String id, String itemId){
        Lost lost = fetchById(id);
        lost.setFoundItem(1);
        lost.setIdIfFound(itemId);
        update(lost);
    }

    public int remove(String nameID) {
        return lostMapper.deleteById(nameID);
    }

    public Lost fetchById(String nameID){
        return lostMapper.selectById(nameID);
    }

    public PageSerializable<Lost> fetchByPage(int size, int current, Lost lost){
        PageHelper.startPage(current, size);
        LambdaQueryWrapper<Lost> lostLambdaQueryWrapper = Wrappers.lambdaQuery();
        lostLambdaQueryWrapper.eq(lost.getName() !=null, Lost::getName, lost.getName());
        List<Lost> losts = lostMapper.selectList(lostLambdaQueryWrapper);
        return PageSerializable.of(losts);
    }

//    // using Pagination in mybatis plus:
//    public Page<Lost> fetchByPage(int size, int current, Lost lost){
//        LambdaQueryWrapper<Lost> lostLambdaQueryWrapper = Wrappers.lambdaQuery();
//        lostLambdaQueryWrapper.eq(lost.getName() !=null, Lost::getName, lost.getName());
//        return lostMapper.selectPage(new Page<>(current, size), lostLambdaQueryWrapper);
//    }
}
