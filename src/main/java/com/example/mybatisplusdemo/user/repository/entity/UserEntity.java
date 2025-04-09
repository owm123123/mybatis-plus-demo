package com.example.mybatisplusdemo.user.repository.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

// @TableName用於指定實體類別對應的資料庫表名，如果不指定，就以類別名稱作為映射關係
// @TableId註解來指定主鍵
// @TableFieId 用於識別實體類別中的欄位與資料庫表中的欄位的對應關係。
//@TableField(condition = "%s&lt;#{%s}") 自定義條件 (不推薦)
// @TableField(exist = false) 排除沒有 column 的欄位 (但因為 entity 跟 dto會切開 所以用不到)
@Data
// autoResultMap = true: 處理 object 跟 db 之間的一映射, 向 jpa 需要定義 @column 一樣
@TableName(value = "user_info", autoResultMap = true)
public class UserEntity {
    // @TableId 設定 PK
    @TableId(type = IdType.AUTO)
    private Integer userId;
    private String username;
    private Integer age;
    private Boolean gender;
    private String remark;
    private LocalDateTime createTime;
    private String createId;
    private LocalDateTime updateTime;
    private String updateId;
    private Boolean enabled;
}
