package com.basicit;

import com.basicit.model.auth.Company;
import com.basicit.model.auth.Permission;
import com.basicit.model.auth.Role;
import com.basicit.model.auth.User;
import com.basicit.service.auth.PermissionService;
import com.basicit.service.auth.RoleService;
import com.basicit.service.auth.UserService;
import com.basicit.web.shiro.vo.PermissionEnumUtil;
import com.basicit.web.shiro.vo.RoleEnumUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class InitServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    /**
     * 역할 생성
     */
    private void addRoles() {
        try {
            roleService.addRole(RoleEnumUtil.ADMIN_ROLE.getRole());
            roleService.addRole(RoleEnumUtil.COMMON_ROLE.getRole());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 메뉴 만들기
     */
    private void addPermission() {
        try {
            List<Permission> permis = PermissionEnumUtil.getPermissions();
            for (Permission permission : permis) {
                permissionService.addPermission(permission);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 사용자 생성
     */
    private void addUsers() {
        try {
            Role adminRole = roleService.findRoleByCode(RoleEnumUtil.ADMIN_ROLE.getRoleCode());
            Role commonRole = roleService.findRoleByCode(RoleEnumUtil.COMMON_ROLE.getRoleCode());
//            Company adminCompany = roleService.

            String password = "123456";


            User admin = new User();
            admin.setUsername("admin");
            admin.setTrueName("관리자");
            admin.setPassword(password);
            admin.setOrganizeId(adminRole.getId());
            userService.addUser(admin, adminRole);

            User nutcracker = new User();
            nutcracker.setUsername("nutcracker");
            nutcracker.setTrueName("Crackers");
            nutcracker.setPassword(password);
            nutcracker.setOrganizeId(commonRole.getId());
            userService.addUser(nutcracker, commonRole);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 역할 승인
    private void bindRolePermission() {
        try {
            Role adminRole = roleService.findRoleByCode(RoleEnumUtil.ADMIN_ROLE.getRoleCode());
            Role commonRole = roleService.findRoleByCode(RoleEnumUtil.COMMON_ROLE.getRoleCode());

            List<Permission> permissionList = PermissionEnumUtil.getPermissions();
            for (Permission permission : permissionList) {
                roleService.addRolePermission(adminRole.getCode(), permission.getSkey());
                roleService.addRolePermission(commonRole.getCode(), permission.getSkey());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void importTestData() {
        try {
            addRoles();
            addPermission();
            addUsers();
            bindRolePermission();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
