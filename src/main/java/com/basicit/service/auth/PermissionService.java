package com.basicit.service.auth;

import com.basicit.model.auth.Permission;
import com.basicit.web.shiro.vo.PermissionVo;

import java.util.List;

public interface PermissionService {

    /**
     * 사용자가 액세스할 수 있는 모든 메뉴 쿼리
     *
     * @param userId 사용자 ID
     * @return permissions 메뉴
     */
    List<PermissionVo> getPermissions(String userId);

    /**
     * 메뉴 추가
     *
     * @param permission 메뉴 아이템
     */
    void addPermission(Permission permission);
}
