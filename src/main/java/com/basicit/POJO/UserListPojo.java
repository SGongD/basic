package com.basicit.POJO;

import lombok.Data;

import java.util.Date;

@Data
public class UserListPojo {
    String id;
    String username;
    String trueName;
    String phoneNum;
    String password;
    String salt;
    Integer status;
    Date createTime;
    Date modifyTime;

    String userId;

    String companyId;
    String companyName;

    String roleId;
    String roleCode;
    String roleRemark;
    String roleName;

    String newPassword;
    String oldPassword;
}
