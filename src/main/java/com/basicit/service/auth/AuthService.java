package com.basicit.service.auth;

import com.basicit.model.auth.Role;
import com.basicit.model.auth.User;

import java.util.List;

/**
 * 어셈블리 권한 인터페이스
 *
 * @author Crackers
 * @date 2022/3/15 14:09
 */
public interface AuthService {

    /**
     * 사용자 이름으로 사용자 쿼리
     *
     * @param username 사용자 이름
     * @return user 사용자
     */
    User findUserByName(String username);

    /**
     * 역할 코드로 역할 쿼리
     *
     * @param roleCode 역할 코딩
     * @return 역할 개체
     */
    Role findRoleByRoleCode(String roleCode);

    /**
     * 역할 코드로 사용자 쿼리
     *
     * @param roleCode 역할 코딩
     * @return user 사용자
     */
    List<User> findUserByRoleCode(String roleCode);

}
