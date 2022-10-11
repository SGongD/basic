package com.basicit.service.auth;

import com.basicit.model.auth.Company;

import java.util.List;

public interface CompanyService {
    Company findCompanyById(String id);

    Company findCompanyByUserId(String userId);

    Company findCompanyByUserId2(String id);
}
