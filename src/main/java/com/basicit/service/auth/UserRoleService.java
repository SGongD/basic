package com.basicit.service.auth;

import com.basicit.model.auth.UserRole;

public interface UserRoleService {
    UserRole findByUserId(String id);
}
