package com.fake.demo.controller;

import com.fake.demo.bean.Result;
import com.fake.demo.bean.entity.Lost;
import com.fake.demo.exception.ExceptionEnum;
import com.fake.demo.exception.LostBaseException;
import com.fake.demo.service.impl.LostServiceImpl;
import com.github.pagehelper.PageSerializable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/lost")
@Slf4j
@RequiredArgsConstructor // inject lostService with a constructor
public class LostController {

    private final LostServiceImpl lostService;

    @GetMapping(value = "/fetchById")
    public Result<Lost> fetchById(@RequestParam String id) {
        return Result.success(lostService.fetchById(id));
    }

    @GetMapping(value = "/fetchById2/{id}")
    public Result<Lost> fetchById2(@PathVariable String id) {
        return Result.success(lostService.fetchById(id));
    }

    @PostMapping(value = "/fetchByPage")
    public Result<PageSerializable<Lost>> fetchByPage(@RequestParam int size, @RequestParam int current, @RequestBody Lost lost){
        return Result.success(lostService.fetchByPage(size, current, lost));
    }

    @PostMapping(value = "/create")
    public Result<String> create(@Valid @RequestBody Lost lost) {
        lostService.create(lost);
        return Result.success();
    }

    @GetMapping(value = "/remove")
    public Result<String> remove(@RequestParam String id){
        if(lostService.remove(id) == 0){
            throw new LostBaseException(ExceptionEnum.ID_NOT_FOUND);
        }
        return Result.success();
    }

    @PostMapping(value = "/update")
    public Result<String> update(@Valid @RequestBody Lost lost){
        if(lostService.update(lost) == 0){
            throw new LostBaseException(ExceptionEnum.ID_NOT_FOUND);
        }
        return Result.success();
    }

    @GetMapping(value = "/updateStatus")
    public Result<String> updateStatus(@RequestParam String id, @RequestParam String itemId){
        lostService.updateStatus(id, itemId);
        return Result.success();
    }
}
