package com.basicit.service.auth;

import com.basicit.model.auth.Role;

import java.util.List;

/**
 * 역할 관련 인터페이스
 *
 * @author Crackers
 * @date 2022/3/15 14:17
 */
public interface RoleService {

    /**
     * 역할 추가 ，동일한 이름의 역할이 이미 존재하는 경우，만들지 마십시오
     *
     * @param role 역할 개체
     */
    void addRole(Role role);

    /**
     * 코드 기반 쿼리 역할
     *
     * @param code 역할 코딩
     * @return
     */
    Role findRoleByCode(String code);

    /**
     * 사용자 쿼리에 따라 모든 역할에 해당
     *
     * @param userId 사용자 Id
     * @return roles 모든 역할
     */
    List<Role> findRoleByUserId(String userId);

    /**
     * 역할 승인
     *
     * @param roleCode      역할 코딩
     * @param permissionKey 해당 KEY 승인
     */
    void addRolePermission(String roleCode, String permissionKey);

}
