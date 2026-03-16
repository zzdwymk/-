package org.example.library.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.library.entity.ReaderInfo;
import org.example.library.mapper.ReaderInfoMapper;
import org.example.library.service.ReaderInfoService;
import org.springframework.stereotype.Service;

@Service
public class ReaderInfoServiceImpl extends ServiceImpl<ReaderInfoMapper, ReaderInfo> implements ReaderInfoService {
}
