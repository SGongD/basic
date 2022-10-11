package com.basicit.model.auth;

import com.basicit.model.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 사용자 개체
 *
 * @author Crackers
 * @date 2022/3/15 14:13
 */
@Data
@TableName("t_sys_user")
public class UserDTO implements BaseEntity<String> {

    private static final long serialVersionUID = 1227495748732942139L;

    /**
     * 기본 키
     */
    @TableId("id")
    private String id;

    /**
     * 로그인 계정
     */
    @TableField("username")
    private String username;

    /**
     * 로그인 비밀번호
     */
    @TableField("password")
    private String password;

    /**
     * salt
     */
    @TableField("salt")
    private String salt;

    /**
     * 실명
     */
    @TableField("true_name")
    private String trueName;

    /**
     * 핸드폰번호
     */
    @TableField("phone_num")
    private String phoneNum;

    /**
     * 상태：0=효율적인，1=유효하지 않은
     */
    @TableField("status")
    private Integer status;

    /**
     * 제독 로그인 시간
     */
    @TableField("last_login_time")
    private Date lastLoginTime;

    /**
     * 생성 시간
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 업데이트 시간
     */
    @TableField("modify_time")
    private Date modifyTime;

    /**
     * 관리자 또는 사용자
     */
    @TableField("role_code")
    private String roleCode;

    /**
     * 회사 ID
     */
    @TableField("company_id")
    private String companyId;

    /**
     * 회사명
     */
    @TableField("company_name")
    private String companyName;

    /**
     * 회사명
     */
    @TableField("role_remark")
    private String roleRemark;

}
