package com.basicit.web.shiro.vo;

import com.basicit.model.auth.Permission;

import java.util.List;

/**
 * Permission VO물체
 *
 * @author Crackers
 * @date 2022/3/15 11:04
 */
public class PermissionVo extends Permission {

    private static final long serialVersionUID = -2051933842290600230L;

    private List<PermissionVo> children;

    public List<PermissionVo> getChildren() {
        return children;
    }

    public void setChildren(List<PermissionVo> children) {
        this.children = children;
    }

}
