package com.basicit.service.auth.impl;

import com.basicit.mapper.auth.UserRoleMapper;
import com.basicit.model.auth.UserRole;
import com.basicit.service.auth.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    public UserRoleMapper userRoleMapper;

    @Override
    public UserRole findByUserId(String userId) {
        UserRole userRole = userRoleMapper.findByUserId(userId);
        return userRole;
    }
}
