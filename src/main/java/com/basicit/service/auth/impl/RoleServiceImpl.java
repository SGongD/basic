package com.basicit.service.auth.impl;

import com.basicit.framework.exception.BusinessException;
import com.basicit.framework.pk.FactoryAboutKey;
import com.basicit.framework.pk.TableEnum;
import com.basicit.mapper.auth.PermissionMapper;
import com.basicit.mapper.auth.RoleMapper;
import com.basicit.mapper.auth.RolePermissionMapper;
import com.basicit.model.auth.Permission;
import com.basicit.model.auth.Role;
import com.basicit.model.auth.RolePermission;
import com.basicit.service.auth.RoleService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 역할 관련 인터페이스
 *
 * @author Crackers
 * @date 2022/3/15 14:17
 */
@Service
public class RoleServiceImpl implements RoleService {

    private static final Logger LOG = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public void addRole(Role role) {
        if (role == null || StringUtils.isBlank(role.getName())) {
            return;
        }
        if (LOG.isDebugEnabled()) {
            LOG.debug("## 역할 추가 : {}", role.getName());
        }
        Role r = findRoleByCode(role.getCode());
        if (r == null) {
            role.setId(FactoryAboutKey.getPK(TableEnum.T_SYS_ROLE));
            roleMapper.insert(role);
        }
    }

    @Override
    public Role findRoleByCode(String code) {
        LOG.debug("## 코드 기반 쿼리 역할 {}", code);
        return roleMapper.findRoleByCode(code);
    }

    @Override
    public List<Role> findRoleByUserId(String userId) {
        return roleMapper.findRoleByUserId(userId);
    }

//    @Override
//    public Role findRoleById(String userId) {
//        return roleMapper.findRoleById(userId);
//    }

    @Override
    public void addRolePermission(String roleCode, String permissionKey) {
        Role role = findRoleByCode(roleCode);
        if (role == null) {
            throw new BusinessException("role-fail", "## 역할을 승인하지 못했습니다.， 역할 코딩错误");
        }
        Permission permis = permissionMapper.findPermissionByKey(permissionKey);
        if (permis == null) {
            throw new BusinessException("role-fail", "## 역할을 승인하지 못했습니다.， 메뉴 키不存在，key=" + permissionKey);
        }

        RolePermission rolePermission = new RolePermission();
        rolePermission.setRoleId(role.getId());
        rolePermission.setPermissionId(permis.getId());

        RolePermission rp = rolePermissionMapper.findRolePermission(rolePermission);
        if (rp == null) {
            rolePermission.setId(FactoryAboutKey.getPK(TableEnum.T_SYS_ROLE_PERMISSION));
            rolePermissionMapper.insert(rolePermission);
        }

    }
}
