package com.basicit.model.auth;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.basicit.model.BaseEntity;
import lombok.Data;

@Data
@TableName("t_sys_company")
public class Company implements BaseEntity<String> {

    /**
     * 기본 키
     */
    @TableId("id")
    private String id;

    /**
     * 회사명
     */
    @TableField("name")
    private String name;
}
