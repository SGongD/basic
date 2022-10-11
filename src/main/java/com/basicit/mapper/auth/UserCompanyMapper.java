package com.basicit.mapper.auth;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.basicit.model.auth.Company;
import com.basicit.model.auth.UserCompany;
import org.apache.ibatis.annotations.Mapper;

/**
 * 사용자 및 회사 관계 개체 Mapper
 *
 * @author SGONGD
 * @date 2022/3/15 14:11
 */
@Mapper
public interface UserCompanyMapper extends BaseMapper<UserCompany> {
    public UserCompany findByUserId(String userId);
}
