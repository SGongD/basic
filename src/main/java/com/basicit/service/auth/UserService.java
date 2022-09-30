package com.basicit.service.auth;

import com.basicit.framework.datasource.PageInfo;
import com.basicit.model.auth.Company;
import com.basicit.model.auth.Role;
import com.basicit.model.auth.User;

import java.util.List;

/**
 * 사용자 관련 인터페이스
 *
 * @author Crackers
 * @date 2022/3/15 14:18
 */
public interface UserService {

    /**
     * 신규 사용자
     *
     * @param user 사용자
     * @param role 역할
     * @return
     */
    boolean addUser(User user, Role role);

    /**
     * 비밀번호 변경
     *
     * @param user 현재 사용자
     */
    void updatePassword(User user);

    /**
     * 사용자 이름으로 사용자 쿼리
     *
     * @param username 사용자 이름
     * @return user 사용자
     */
    User findUserByName(String username);

    /**
     * 사용자 로그인 시간 업데이트
     *
     * @param user 사용자
     */
    void updateUserLastLoginTime(User user);

    /**
     * 조직 아래의 모든 고객 서비스 직원 질의
     *
     * @return
     */
    List<User> findUsers();

    /**
     * 조건에 따라（정리하다、이름）쿼리 사용자
     *
     * @param shopId  조직 ID
     * @param empName 사용자 이름
     * @return
     */
    List<User> findEmp(String shopId, String empName);

    PageInfo<User> findUserByPage(Integer pageNum, String Keywords);

    List<User> findUserByKeywords(String keywords);
}
