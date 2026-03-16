package org.example.library.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.library.common.Result;
import org.example.library.entity.BorrowRecord;
import org.example.library.service.BorrowRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/borrow")
public class BorrowRecordController {
    @Autowired
    private BorrowRecordService borrowRecordService;

    /**
     * 获取借阅记录列表
     */
    @GetMapping
    public Result getRecord() {
        return Result.success(borrowRecordService.list());
    }

    /**
     * 根据ID获取借阅记录
     */
    @GetMapping("/{id}")
    public Result getRecordById(@PathVariable Long id) {
        System.out.println("get: " + id);
        return Result.success(borrowRecordService.getById(id));
    }

    /**
     * 添加借阅记录
     */
    @PostMapping
    public Result addRecord(@RequestBody BorrowRecord borrowRecord) {
        System.out.println(borrowRecord);
        return Result.success(borrowRecordService.save(borrowRecord));
    }

    /**
     * 更新借阅记录
     */
    @PutMapping
    public Result updateRecord(@RequestBody BorrowRecord borrowRecord) {
        System.out.println(borrowRecord);
        return Result.success(borrowRecordService.updateById(borrowRecord));
    }

    /**
     * 删除借阅记录
     */
    @DeleteMapping("/{id}")
    public Result deleteRecord(@PathVariable Long id) {
        System.out.println("remove: " + id);
        return Result.success(borrowRecordService.removeById(id));
    }

    /**
     * 分页查询借阅记录
     */
    @GetMapping("/page")
    public Result findPage(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "") String BorrowNo
    ) {
        return Result.success(borrowRecordService.page(new Page<>(pageNum, pageSize),
                new LambdaQueryWrapper<BorrowRecord>()
                        .like(BorrowRecord::getBorrowNo, BorrowNo)
        ));
    }
}
