package com.basicit.mapper.auth;

import com.basicit.POJO.UserListPojo;
import com.basicit.framework.datasource.PageInfo;
import com.basicit.model.auth.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.basicit.model.auth.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 사용자Mapper
 *
 * @author Crackers
 * @date 2022/3/15 14:13
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    User findUserByName(String username);

    UserDTO findUserById(String userId);

    User findUserById2(String id);


    int updateByFullId(UserDTO user);
    /**
     * 조직의 모든 사용자 쿼리
     *
     * @param organizeId 조직 ID
     * @return
     */
    List<User> findUserByShop(String organizeId);

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
    List<User> findEmp(String roleCode, Integer status, String shopId, String empName);

    /**
     * 사용자 이름으로 사용자 쿼리
     *
     * @param username 사용자 이름
     * @return user 사용자
     */
    List<User> findUserByRoleCode(@Param("roleCode") String username);

    List<User> findUserByCompanyId(@Param("companyCode") String username);

    /**
     * 페이지를 조회하는 쿼리
     *
     * @param page 페이지
     * @param keywords 키워드(검색어)
     * @return page 페이지
     */
    PageInfo<User> findUserByPage(PageInfo<User> page, @Param("keywords") String keywords);

    List<User> findUserByKeywords(@Param("keywords") String keywords);

}
