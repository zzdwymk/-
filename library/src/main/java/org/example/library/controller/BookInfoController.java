package org.example.library.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.library.common.Result;
import org.example.library.entity.BookInfo;
import org.example.library.service.BookInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookInfoController {

    @Autowired
    private BookInfoService bookInfoService;

    /**
     * 录入书籍数据
     */
    @PostMapping
    public Result addBook(@RequestBody BookInfo bookInfo) {
        System.out.println(bookInfo);
        return Result.success(bookInfoService.save(bookInfo));
    }

    /**
     * 获取书籍列表
     * */
    @GetMapping
    public Result getBook() {
        return Result.success(bookInfoService.list());
    }

    /**
     * 获取指定 ID 书籍数据
     * */
    @GetMapping("/{id}")
    public Result getBookById(@PathVariable Long id) {
        System.out.println("get: " + id);
        return Result.success(bookInfoService.getById(id));
    }

    /**
     * 更新书籍数据
     * */
    @PutMapping
    public Result updateBook(@RequestBody BookInfo bookInfo) {
        System.out.println(bookInfo);
        return Result.success(bookInfoService.updateById(bookInfo));
    }

    /**
     * 删除指定 ID 书籍数据
     * */
    @DeleteMapping("/{id}")
    public Result deleteBook(@PathVariable Long id) {
        System.out.println("remove: " + id);
        return Result.success(bookInfoService.removeById(id));
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
        LambdaQueryWrapper<BookInfo> queryWrapper = new LambdaQueryWrapper<>();
        if (name != null) {
            queryWrapper.like(BookInfo::getBookName, name);
        }
        return Result.success(bookInfoService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }
}
