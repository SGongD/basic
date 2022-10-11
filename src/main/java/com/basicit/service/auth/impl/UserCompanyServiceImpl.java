package com.basicit.service.auth.impl;

import com.basicit.mapper.auth.UserCompanyMapper;
import com.basicit.model.auth.UserCompany;
import com.basicit.service.auth.UserCompanyService;
import org.springframework.beans.factory.annotation.Autowired;

public class UserCompanyServiceImpl implements UserCompanyService {

    @Autowired
    public UserCompanyMapper userCompanyMapper;

    public UserCompany findByUserId(String id) {
        UserCompany userCompany = userCompanyMapper.findByUserId(id);
        return userCompany;
    }

}
