package org.example.library.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.library.entity.BookInfo;
import org.example.library.mapper.BookInfoMapper;
import org.example.library.service.BookInfoService;
import org.springframework.stereotype.Service;

@Service
public class BookInfoServiceImpl extends ServiceImpl<BookInfoMapper, BookInfo> implements BookInfoService {
}
