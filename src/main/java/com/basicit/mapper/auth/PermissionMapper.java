package com.basicit.mapper.auth;

import com.basicit.model.auth.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * 메뉴  Mapper
 *
 * @author Crackers
 * @date 2022/3/15 14:12
 */
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {

    /**
     * 사용자가 액세스할 수 있는 모든 메뉴 쿼리
     *
     * @param userId 사용자
     * @return permissions 메뉴
     */
    List<Permission> findPermissionByUserId(String userId);

    /**
     * 메뉴 KEY에 따라 메뉴 쿼리
     *
     * @param permissionKey 메뉴 키
     * @return
     */
    Permission findPermissionByKey(String permissionKey);
}
