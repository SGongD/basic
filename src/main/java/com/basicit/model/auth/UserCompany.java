package com.basicit.model.auth;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_user_company")
public class UserCompany {

    /**
     * 기본 키
     */
    @TableId("id")
    private String id;

    /**
     * 사용자 테이블 기본 키
     */
    @TableField("user_id")
    private String userId;

    /**
     * 회사 테이블 기본 키
     */
    @TableField("company_id")
    private String companyId;

}
