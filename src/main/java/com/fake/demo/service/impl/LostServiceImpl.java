package com.fake.demo.service.impl;

import com.fake.demo.bean.entity.Lost;
import com.fake.demo.mapper.LostMapper;
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
        lostMapper.create(lost);
    }

    public int update(Lost lost) {
        return lostMapper.update(lost);
    }

    public void updateStatus(String id, String itemId){
        Lost lost = fetchById(id);
        lost.setFoundItem(1);
        lost.setIdIfFound(itemId);
        update(lost);
    }

    public int remove(String nameID) {
        return lostMapper.remove(nameID);
    }

    public Lost fetchById(String nameID){
        return lostMapper.fetchById(nameID);
    }

    public List<Lost> fetchByPage(int size, int page){
        int offset = (page - 1) * size;
        return lostMapper.fetchByPage(size, offset);
    }
}
