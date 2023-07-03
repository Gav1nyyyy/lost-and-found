package com.fake.demo.controller;

import com.fake.demo.bean.Result;
import com.fake.demo.bean.entity.Lost;
import com.fake.demo.service.impl.LostServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/lost")
@Slf4j
public class LostController {

    LostServiceImpl lostService = new LostServiceImpl();

    @GetMapping(value = "/fetchById")
    public Result<Lost> fetchById(@RequestParam String id) {
        return Result.success(lostService.fetchById(id));
    }

    @GetMapping(value = "/fetchById2/{id}")
    public Result<Lost> fetchById2(@PathVariable String id) {
        return Result.success(lostService.fetchById(id));
    }

    @GetMapping(value = "/fetchByPage")
    public Result<List<Lost>> fetchByPage(@RequestParam int size, @RequestParam int page){
        return Result.success(lostService.fetchByPage(size, page));
    }

    @PostMapping(value = "/create")
    public Result<String> create(@Valid @RequestBody Lost lost) {
        lostService.create(lost);
        return Result.success();
    }

    @GetMapping(value = "/remove/{id}")
    public Result<String> remove(@PathVariable String id){
        lostService.remove(id);
        return Result.success();
    }

    @PostMapping(value = "/update")
    public Result<String> update(@Valid @RequestBody Lost lost){
        lostService.update(lost);
        return Result.success();
    }

    @GetMapping(value = "/updateStatus")
    public Result<String> updateStatus(@RequestParam String id, @RequestParam String itemId){
        lostService.updateStatus(id, itemId);
        return Result.success();
    }
}