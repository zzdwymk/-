package org.example.library.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.library.entity.ReturnRecord;
import org.example.library.mapper.ReturnRecordMapper;
import org.example.library.service.ReturnRecordService;
import org.springframework.stereotype.Service;

@Service
public class ReturnRecordServiceImpl extends ServiceImpl<ReturnRecordMapper, ReturnRecord> implements ReturnRecordService {
}
