package org.example.library.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.library.common.Result;
import org.example.library.entity.ReturnRecord;
import org.example.library.service.ReturnRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/return")
public class ReturnRecordController {

    @Autowired
    private ReturnRecordService returnRecordService;

    /**
     * 获取所有还书记录
     * */
    @GetMapping
    public Result getReturnRecord() {
        return Result.success(returnRecordService.list());
    }

    /**
     * 获取指定还书记录
     * */
    @GetMapping("/{id}")
    public Result getReturnRecordByUserId(@PathVariable String id) {
        System.out.println("get: " + id);
        return Result.success(returnRecordService.getById(id));
    }

    /**
     * 添加还书记录
     * */
    @PostMapping
    public Result addReturnRecord(@RequestBody ReturnRecord returnRecord) {
        System.out.println(returnRecord);
        return Result.success(returnRecordService.save(returnRecord));
    }

    /**
     * 更新还书记录
     * */
    @PutMapping
    public Result updateReturnRecord(@RequestBody ReturnRecord returnRecord) {
        System.out.println(returnRecord);
        return Result.success(returnRecordService.updateById(returnRecord));
    }

    /**
     * 删除还书记录
     * */
    @DeleteMapping("/{id}")
    public Result deleteReturnRecord(@PathVariable Long id) {
        System.out.println(id);
        return Result.success(returnRecordService.removeById(id));
    }

    /**
     * 分页查询还书记录
     * */
    @GetMapping("/page")
    public Result findPage(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "") String name
    ) {
        LambdaQueryWrapper<ReturnRecord> queryWrapper = new LambdaQueryWrapper<>();
        if (name != null) {
            queryWrapper.like(ReturnRecord::getReturnNo, name);
        }
        return Result.success(returnRecordService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }
}
