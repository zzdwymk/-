package org.example.library.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.library.entity.BookInfo;

@Mapper
public interface BookInfoMapper extends BaseMapper<BookInfo> {
}
