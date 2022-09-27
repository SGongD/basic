package com.basicit.mapper.auth;

import com.basicit.model.auth.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 사용자 및 역할 관계 개체 Mapper
 *
 * @author Crackers
 * @date 2022/3/15 14:11
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {

}
