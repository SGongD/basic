package com.basicit.web.controller;

import com.basicit.POJO.UserPOJO;
import com.basicit.framework.datasource.PageInfo;
import com.basicit.model.auth.Company;
import com.basicit.model.auth.Role;
import com.basicit.model.auth.User;
import com.basicit.service.auth.RoleService;
import com.basicit.service.auth.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

//    private CompanyService companyService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @PostMapping("/view/user/user_add")
    @ResponseBody
    public Map<String, String> add(UserPOJO user) {
        User savedUser = new User();
        savedUser.setUsername(user.getUsername());
        savedUser.setPassword(user.getUserPassword());
        savedUser.setTrueName(user.getTrueName());
        savedUser.setOrganizeId(user.getRole());
        savedUser.setBusiness(user.getBusiness());
        savedUser.setPhoneNum(user.getPhoneNum());
        Role role = roleService.findRoleByCode(user.getRole());
//        Company company = companyService.findCompanyById(user.getCompany());
        boolean flag = userService.addUser(savedUser, role);
        return getResultMap(flag);
    }

    private Map<String, String> getResultMap(boolean flag) {
        Map<String, String> result = new HashMap<>();
        if(flag) {
            result.put("status", "1");
            result.put("msg", "성공적으로 게시됨");
        } else {
            result.put("status", "0");
            result.put("msg", "게시 실패");
        }
        return result;
    }

    @GetMapping("view/user/user_list")
    public String user_list(ModelMap usermap) {
        PageInfo<User> page = userService.findUserByPage(null, null);
        log.info("pppppage = {}", page);
        usermap.put("page", page);
        return "view/user/user_list";
    }

    @PostMapping("view/user/user_list_page")
    public String user_list_page(@RequestParam(value="keywords", required = false) String keywords, @RequestParam(value = "pageNum", required = false) Integer pageNum, ModelMap map) {
        log.info("#user pagination 쿼리 뉴스 pageNum={} , keywords={}", pageNum, keywords);
        PageInfo<User> page = userService.findUserByPage(pageNum, keywords);
        map.put("page", page);
        map.put("keywords", keywords);
        return "view/user/user_list_page";
    }
}
