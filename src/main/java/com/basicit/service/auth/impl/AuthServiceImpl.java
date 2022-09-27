package com.basicit.service.auth.impl;

import com.basicit.mapper.auth.RoleMapper;
import com.basicit.mapper.auth.UserMapper;
import com.basicit.model.auth.Role;
import com.basicit.model.auth.User;
import com.basicit.service.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 어셈블리 권한 인터페이스
 *
 * @author Crackers
 * @date 2022/3/15 14:09
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public User findUserByName(String username) {
        return userMapper.findUserByName(username);
    }

    @Override
    public Role findRoleByRoleCode(String roleCode) {
        return roleMapper.findRoleByCode(roleCode);
    }

    @Override
    public List<User> findUserByRoleCode(String roleCode) {
        return userMapper.findUserByRoleCode(roleCode);
    }

}
