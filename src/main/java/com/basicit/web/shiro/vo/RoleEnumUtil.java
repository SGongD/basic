package com.basicit.web.shiro.vo;

import com.basicit.model.auth.Role;

/**
 * 역할 열거
 *
 * @author Principal
 * @date 2022/3/15 11:05
 */
public enum RoleEnumUtil {

    ADMIN_ROLE("관리자", "admin_role", "관리자"),
    COMMON_ROLE("사용자", "common_role", "사용자");

    RoleEnumUtil(String name, String roleCode, String remark) {
        this.name = name;
        this.roleCode = roleCode;
        this.remark = remark;
    }

    private String name;
    private String roleCode;
    private String remark;

    public Role getRole() {
        Role role = new Role();
        role.setName(this.name());
        role.setCode(this.roleCode);
        role.setRemark(this.remark);
        return role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
