package com.example.mybatisplusdemo.user.repository.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName(value = "order_info", autoResultMap = true)
public class OrderEntity {
    @TableId
    private String orderId;
    private String userId;
    private String orderNumber;
    private BigDecimal totalAmount;
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
