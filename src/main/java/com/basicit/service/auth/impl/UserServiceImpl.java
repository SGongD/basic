package com.basicit.service.auth.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.basicit.framework.constant.Constants;
import com.basicit.framework.datasource.DataSourceTagger;
import com.basicit.framework.datasource.PageInfo;
import com.basicit.framework.exception.BusinessException;
import com.basicit.framework.pk.FactoryAboutKey;
import com.basicit.framework.pk.TableEnum;
import com.basicit.mapper.auth.*;
import com.basicit.model.auth.*;
import com.basicit.service.auth.UserService;
import com.basicit.util.salt.Digests;
import com.basicit.util.salt.Encodes;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;

/**
 * 사용자 관련 인터페이스
 *
 * @author Crackers
 * @date 2022/3/15 14:18
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    public static final String HASH_ALGORITHM = "SHA-1";
    public static final int HASH_INTERATIONS = 1024;
    private static final int SALT_SIZE = 8;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private UserCompanyMapper userCompanyMapper;

    @Autowired
    private CompanyMapper CompanyMapper;

    @Autowired
    private UserMapper userMapper;

    /**
     * 안전한 비밀번호 설정，랜덤 생성 salt그리고 1024번 후에 sha-1 hash
     */
    private void entryptPassword(User user) {
        byte[] salt = Digests.generateSalt(SALT_SIZE);
        user.setSalt(Encodes.encodeHex(salt));

        byte[] hashPassword = Digests.sha1(user.getPassword().getBytes(), salt, HASH_INTERATIONS);
        user.setPassword(Encodes.encodeHex(hashPassword));
    }

    @Transactional(rollbackFor = BusinessException.class)
    @Override
    public boolean addUser(User user, Role role) {
        if (user == null || role == null || StringUtils.isAnyBlank(user.getUsername(), user.getPassword())) {
            throw new BusinessException("user.registr.error", "잘못된 등록 정보");
        }

        if (StringUtils.isBlank(role.getId())) {
            throw new BusinessException("user.registr.error", "사용자가 역할을 지정하지 않음");
        }

        Role r = roleMapper.selectById(role.getId());
        if (r == null) {
            throw new BusinessException("user.registr.error", "사용자가 조직 또는 역할을 지정하지 않음");
        }

//        Company c = CompanyMapper.selectById(company.getId());
//        if (c == null) {
//            throw new BusinessException("user.registr.error", "사용자가 회사를 지정하지 않음");
//        }

        User u = userMapper.findUserByName(user.getUsername());
        if (u != null) {
            throw new BusinessException("user.registr.error", "사용자 계정이 이미 존재합니다.,username=" + user.getUsername());
        }

        // User_Role 자동추가 부분
        entryptPassword(user);
        user.setStatus(Constants.STATUS_VALID);
        user.setCreateTime(Calendar.getInstance().getTime());
        user.setId(FactoryAboutKey.getPK(TableEnum.T_SYS_USER));
        userMapper.insert(user);

        UserRole ur = new UserRole();
        ur.setRoleId(r.getId());
        ur.setUserId(user.getId());
        ur.setId(FactoryAboutKey.getPK(TableEnum.T_SYS_USER_ROLE));
        userRoleMapper.insert(ur);

//        UserCompany uc = new UserCompany();
//        uc.setCompanyId(r.getId());
//        uc.setUserId(user.getId());
//        uc.setId(FactoryAboutKey.getPK(TableEnum.T_SYS_USER_COMPANY));
//        userCompanyMapper.insert(uc);

        return false;
    }

    @Override
    public boolean editUser(User user) {
        if(user != null && StringUtils.isNotBlank(user.getId())) {
            int flag = userMapper.updateById(user);
            return flag == 1;
        } else {
            return false;
        }
    }

    @Override
    public User findUserById(String id) {
        if(StringUtils.isBlank(id)) {
            return null;
        } else {
            return userMapper.selectById(id);
        }
    }


    @Override
    public void updatePassword(User user) {
        if (log.isDebugEnabled()) {
            log.debug("## update User password.");
        }
        User u = userMapper.selectById(user.getId());
        u.setPassword(user.getPassword());
        entryptPassword(u);
        u.setModifyTime(Calendar.getInstance().getTime());
        userMapper.updateById(u);
    }

    @Override
    public User findUserByName(String username) {
        try {
            return userMapper.findUserByName(username);
        } catch (Exception e) {
            log.error("# 계정별 사용자 조회 및 오류 보고 , username={}", username);
            throw new BusinessException("1001", "사용자 쿼리 실패");
        }
    }

    @Override
    public void updateUserLastLoginTime(User user) {
        User u = userMapper.selectById(user.getId());
        if (u != null) {
            user = new User();
            user.setLastLoginTime(Calendar.getInstance().getTime());
            user.setId(u.getId());
            userMapper.updateById(u);
        }
    }

    @Override
    public List<User> findUsers() {
        return userMapper.findUsers();
    }

    @Override
    public List<User> findEmp(String shopId, String empName) {
        return userMapper.findEmp(Constants.COMMON_ROLE_CODE, Constants.STATUS_VALID, shopId, empName);
    }

    @Override
    public List<User> findUserByKeywords(String keywords) {
        log.info("# 유저 데이터베이스 쿼리");
        return userMapper.findUserByKeywords(keywords);
    }

    @Override
    public PageInfo<User> findUserByPage(Integer pageNum, String keywords){
        PageInfo<User> resultPage = findByPage(pageNum, keywords);
        log.info("#User Database resultPage={}", resultPage);
        return resultPage;
    }
    @DS(DataSourceTagger.DB1)
    private PageInfo<User> findByPage(Integer pageNum, String keywords) {
        log.debug("# pageNum={},keywords={}", pageNum, keywords);
        if (pageNum == null) {
            pageNum = 1;
        }
        PageInfo<User> page = new PageInfo<>(pageNum, Constants.PAGE_SIZE);
        return userMapper.findUserByPage(page, keywords);
    }
}
