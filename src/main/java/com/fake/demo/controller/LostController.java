package com.fake.demo.controller;

import com.fake.demo.bean.Result;
import com.fake.demo.bean.entity.Lost;
import com.fake.demo.exception.NoSuchIdException;
import com.fake.demo.service.impl.LostServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

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
        if(lostService.remove(id) == 0){
            throw new NoSuchIdException();
        }
        return Result.success();
    }

    @PostMapping(value = "/update")
    public Result<String> update(@Valid @RequestBody Lost lost){
        if(lostService.update(lost) == 0){
            throw new NoSuchIdException();
        }
        return Result.success();
    }

    @GetMapping(value = "/updateStatus")
    public Result<String> updateStatus(@RequestParam String id, @RequestParam String itemId){
        lostService.updateStatus(id, itemId);
        return Result.success();
    }
}
