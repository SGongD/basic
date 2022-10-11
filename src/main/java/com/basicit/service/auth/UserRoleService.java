package com.basicit.service.auth;

import com.basicit.model.auth.UserRole;

public interface UserRoleService {
    public UserRole getByUserId(String id);
}
