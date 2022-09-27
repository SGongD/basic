package com.basicit.mapper.auth;

import com.basicit.model.auth.RolePermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 역할과 메뉴 관계Mapper
 *
 * @author Crackers
 * @date 2022/3/15 14:14
 */
@Mapper
public interface RolePermissionMapper extends BaseMapper<RolePermission> {

    RolePermission findRolePermission(RolePermission per);

}
