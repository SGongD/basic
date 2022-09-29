package com.basicit.mapper.auth;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.basicit.model.auth.Company;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CompanyMapper extends BaseMapper<Company> {

    /**
     * 사용자 쿼리에 따라 모든 회사명에 해당
     *
     * @param userId 사용자
     * @return name 회사명
     */
    List<Company> findCompanyByUserId(String userId);

    Company findCompanyById(String Id);

}
