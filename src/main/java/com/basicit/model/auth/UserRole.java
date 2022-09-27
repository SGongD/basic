package com.basicit.model.auth;

import com.basicit.model.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


/**
 * 사용자 및 역할 관계 개체
 *
 * @author Crackers
 * @date 2022/3/15 14:11
 */
@Data
@TableName("t_sys_user_role")
public class UserRole implements BaseEntity<String> {

    private static final long serialVersionUID = -56720255622342923L;


    /**
     * 기본 키
     */
    @TableId("id")
    private String id;

    /**
     * 역할 테이블 기본 키
     */
    @TableField("role_id")
    private String roleId;

    /**
     * 사용자 테이블 기본 키
     */
    @TableField("user_id")
    private String userId;

}
