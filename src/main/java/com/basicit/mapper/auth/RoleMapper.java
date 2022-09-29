package com.basicit.mapper.auth;

import com.basicit.model.auth.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * 역할 개체 Mapper
 *
 * @author Crackers
 * @date 2022/3/15 14:12
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 사용자 쿼리에 따라 모든 역할에 해당
     *
     * @param userId 사용자
     * @return roles 모든 역할
     */
    List<Role> findRoleByUserId(String userId);

    /**
     * 코드 기반 쿼리 역할
     *
     * @param code 역할 코딩
     * @return
     */
    Role findRoleByCode(String code);

    Role findRoleById(String Id);

}
