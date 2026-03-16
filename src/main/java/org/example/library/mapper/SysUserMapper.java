package org.example.library.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.library.entity.SysUser;

@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
}
