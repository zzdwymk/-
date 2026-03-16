package org.example.library.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.library.entity.SysUser;
import org.example.library.mapper.SysUserMapper;
import org.example.library.service.SysUserService;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
}
