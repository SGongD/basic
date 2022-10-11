package com.basicit.service.auth.impl;

import com.basicit.mapper.auth.CompanyMapper;
import com.basicit.model.auth.Company;
import com.basicit.service.auth.CompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {
    private static final Logger LOG = LoggerFactory.getLogger(CompanyServiceImpl.class);

    @Autowired
    private CompanyMapper companyMapper;

    @Override
    public Company findCompanyById(String id) {
        LOG.debug("## 코드 기반 쿼리 역할 {}", id);
        return companyMapper.findCompanyById(id);
    }

    @Override
    public Company findCompanyByUserId(String id) {
        return companyMapper.findCompanyByUserId(id);
    }

    @Override
    public Company findCompanyByUserId2(String id) {
        return null;
    }
}
