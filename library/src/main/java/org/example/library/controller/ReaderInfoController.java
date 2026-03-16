package org.example.library.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.library.common.Result;
import org.example.library.entity.ReaderInfo;
import org.example.library.service.ReaderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reader")
public class ReaderInfoController {
    @Autowired
    private ReaderInfoService readerInfoService;

    /**
     * 获取读者列表
     * */
    @GetMapping
    public Result getReader() {
        return Result.success(readerInfoService.list());
    }

    /**
     * 根据id获取读者
     * */
    @GetMapping("/{id}")
    public Result getReaderById(@PathVariable Integer id) {
        System.out.println("get: " + id);
        return Result.success(readerInfoService.getById(id));
    }

    /**
     * 添加读者
     * */
    @PostMapping
    public Result addReader(@RequestBody ReaderInfo readerInfo) {
        System.out.println(readerInfo);
        return Result.success(readerInfoService.save(readerInfo));
    }

    /**
     * 更新读者
     * */
    @PutMapping
    public Result updateReader(@RequestBody ReaderInfo readerInfo) {
        System.out.println(readerInfo);
        return Result.success(readerInfoService.updateById(readerInfo));
    }

    /**
     * 删除读者
     * */
    @DeleteMapping("/{id}")
    public Result deleteReader(@PathVariable Long id) {
        System.out.println("delete: " + id);
        return Result.success(readerInfoService.removeById(id));
    }

    /**
     * 分页查询书籍数据
     * */
    @GetMapping("/page")
    public Result findPage(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "") String name
    ) {
        LambdaQueryWrapper<ReaderInfo> queryWrapper = new LambdaQueryWrapper<>();
        if (name != null) {
            queryWrapper.like(ReaderInfo::getReaderName, name);
        }
        return Result.success(readerInfoService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }

}
