package org.example.library.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.library.entity.BorrowRecord;
import org.example.library.mapper.BorrowRecordMapper;
import org.example.library.service.BorrowRecordService;
import org.springframework.stereotype.Service;

@Service
public class BorrowRecordServiceImpl extends ServiceImpl<BorrowRecordMapper, BorrowRecord> implements BorrowRecordService {
}
