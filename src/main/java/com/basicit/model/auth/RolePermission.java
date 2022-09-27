package com.basicit.model.auth;

import com.basicit.model.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 역할 및 메뉴 관계 개체
 *
 * @author Crackers
 * @date 2022/3/15 14:14
 */
@Data
@TableName("t_sys_role_permission")
public class RolePermission implements BaseEntity<String> {

    private static final long serialVersionUID = -7948507636703811294L;

    /**
     * 기본 키
     */
    @TableId("id")
    private String id;

    /**
     * 메뉴 테이블 기본 키
     */
    @TableField("permission_id")
    private String permissionId;

    /**
     * 역할 테이블 기본 키
     */
    @TableField("role_id")
    private String roleId;
}
