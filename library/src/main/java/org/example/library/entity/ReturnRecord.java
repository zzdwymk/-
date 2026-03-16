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
@TableName("Return_Record")
public class ReturnRecord {
    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField("return_no")
    private String returnNo;

    @TableField("borrow_id")
    private Long borrowId;

    @TableField("return_date")
    private LocalDateTime returnDate;

    @Value("0.00")
    private Double fineAmount;

    @TableField("fine_reason")
    private String fineReason;

    @TableField("operator")
    private String operator;

    @TableField(value = "create_Time", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    @TableField(value = "update_Time", fill = FieldFill.UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;
}
