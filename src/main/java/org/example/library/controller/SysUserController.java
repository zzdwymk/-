package org.example.library.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.library.common.Result;
import org.example.library.entity.SysUser;
import org.example.library.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 录入用户
     * */
    @PostMapping
    public Result addUser(@RequestBody SysUser sysUser) {
        System.out.println(sysUser);
        return Result.success(sysUserService.save(sysUser));
    }

    /**
     * 获取用户数据列表
     * */
    @GetMapping
    public Result getUser() {
        return Result.success(sysUserService.list());
    }

    /**
     * 获取指定 ID 用户数据
     * */
    @GetMapping("/{id}")
    public Result getUserById(@PathVariable Long id) {
        return Result.success(sysUserService.getById(id));
    }

    /**
     * 更新用户数据
     * */
    @PutMapping
    public Result updateUser(@RequestBody SysUser sysUser) {
        System.out.println(sysUser);
        return Result.success(sysUserService.updateById(sysUser));
    }

    /**
     * 删除指定 ID 用户数据
     * */
    @DeleteMapping("/{id}")
    public Result deleteUser(@PathVariable Long id){
        return Result.success(sysUserService.removeById(id));
    }

    /**
     * 分页查询用户数据
     * */
    @GetMapping("/page")
    public Result findPage(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "") String name
    ) {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        if (name != null) {
            queryWrapper.like(SysUser::getUsername, name);
        }
        return Result.success(sysUserService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }
}
