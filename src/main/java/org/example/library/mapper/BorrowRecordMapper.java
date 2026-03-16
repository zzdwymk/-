package org.example.library.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.library.entity.BorrowRecord;

@Mapper
public interface BorrowRecordMapper extends BaseMapper<BorrowRecord> {
}
