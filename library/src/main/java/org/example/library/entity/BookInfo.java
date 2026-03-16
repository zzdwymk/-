package org.example.library.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;

@Data
@TableName("Book_Info")
public class BookInfo {
    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField("book_no")
    private String bookNo;

    @TableField("book_name")
    private String bookName;

    @TableField("author")
    private String author;

    @TableField("publisher")
    private String publisher;

    @TableField("publish_date")
    private String publishDate;

    @TableField("category")
    private String category;

    @Value("0")
    private Long stock;

    @Value("NORMAL")
    private String status;

    @TableField(value = "create_Time", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    @TableField(value = "update_Time", fill = FieldFill.UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;
}
