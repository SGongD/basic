package com.basicit.mapper.auth;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.basicit.model.auth.Company;
import com.basicit.model.auth.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CompanyMapper extends BaseMapper<Company> {

    /**
     * 사용자 쿼리에 따라 모든 회사에 해당
     *
     * @param userId 사용자
     * @return roles 모든 역할
     */
    Company findCompanyByUserId(String userId);

    /**
     * 사용자 쿼리에 따라 모든 회사명에 해당
     *
     * @param id 회사Id
     * @return name 회사명
     */
    Company findCompanyById(String id);


    Company findCompanyByUserId2(String userId);

}
