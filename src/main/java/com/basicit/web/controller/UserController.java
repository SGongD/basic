package com.basicit.web.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.basicit.POJO.UserListPojo;
import com.basicit.POJO.UserPojo;
import com.basicit.POJO.UserPwPojo;
import com.basicit.framework.datasource.PageInfo;
import com.basicit.model.auth.Company;
import com.basicit.model.auth.Role;
import com.basicit.model.auth.User;
import com.basicit.model.auth.UserDTO;
import com.basicit.service.auth.CompanyService;
import com.basicit.service.auth.RoleService;
import com.basicit.service.auth.UserService;
import com.basicit.service.auth.impl.UserServiceImpl;
import com.basicit.util.salt.Digests;
import com.basicit.util.salt.Encodes;
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

    @Autowired
    private CompanyService companyService;


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @PostMapping("/view/user/user_add")
    @ResponseBody
    public Map<String, String> add(UserPojo user) {
        User savedUser = new User();
        savedUser.setUsername(user.getUsername());
        savedUser.setPassword(user.getUserPassword());
        savedUser.setTrueName(user.getTrueName());
        savedUser.setPhoneNum(user.getPhoneNum());
        Role role = roleService.findRoleByCode(user.getRole());
        Company company = companyService.findCompanyById(user.getBusiness());
        boolean flag = userService.addUser(savedUser, role, company);
        return getResultMap(flag);
    }

    @PostMapping("/user/edit_password")
    @ResponseBody
    public boolean edit_password(UserPwPojo pwPojo) {
        // UserPwPojo ????????? ?????????
        // existUser ?????? (UserDTO????????? ??? ?????????)
        User existUser = userService.findUserById2(pwPojo.getId());

        User user = new User();

        // ????????? ??????(?????????) 
        byte[] hashPassword = Digests.sha1(pwPojo.getOldPw().getBytes(), Encodes.decodeHex(existUser.getSalt()),
                                            UserServiceImpl.HASH_INTERATIONS);
        user.setPassword(Encodes.encodeHex(hashPassword));

        if(!(user.getPassword()).equals(existUser.getPassword())) {
            return false;
        }

        existUser.setPassword(pwPojo.getNewPw());
        existUser.setSalt(null);

        userService.encrypt(existUser);
        userService.changePw(existUser);

        return true;
    }


    public Map<String, String> edit_password(@ModelAttribute("resetform") User user) {
        log.info("user = ", user);
        userService.updatePassword(user);
        boolean flag = userService.editUser(user);
        return getResultMap(flag);
    }

    private Map<String, String> getResultMap(boolean flag) {
        Map<String, String> result = new HashMap<>();
        if(flag) {
            result.put("status", "1");
            result.put("msg", "??????????????? ?????????");
        } else {
            result.put("status", "0");
            result.put("msg", "?????? ??????");
        }
        return result;
    }


    // ????????? ?????? ????????? ?????? ??? load
    @GetMapping("/user/load/{id}")
    // @PathVariable : ????????? ?????? ?????? ?????? ???????????? ?????? ????????? ??? ???????????? ??? {id}
    public String load(@PathVariable String id, ModelMap map) {
        log.info("# ajax????????? ?????? ?????????");
        // id?????? ????????? user ?????? ?????? ????????? ??????
        UserDTO user = userService.findUserById(id);
        log.info("pageLoad :: UserDTO = " + user);
        // addAttribute: hashMap????????? ???????????? key-value ?????? Return ?????????.
        // map????????? ?????? Key - Value ??? ????????? ???????????? ???????????? ????????????(Key??? ????????? ???)
        map.addAttribute("user", user);
        return "view/user/user_edit_form";
    }

    @PostMapping("/user/user_edit_form")
    @ResponseBody
    public Map<String, String> edit(@ModelAttribute("userForm") UserDTO user) {
        boolean flag = userService.editFullUser(user);
        return getResultMap(flag);
    }


    @GetMapping("view/user/user_list")
    public String user_list(ModelMap userDTOmap) {
        PageInfo<User> page = userService.findUserByPage(null, null);
        log.info("pppppage = {}", page);
        userDTOmap.put("page", page);
        return "view/user/user_list";
    }

    @PostMapping("/user/user_list_page")
    public String user_list_page(@RequestParam(value="keywords", required = false) String keywords, @RequestParam(value = "pageNum", required = false) Integer pageNum, ModelMap map) {
        log.info("#user pagination ?????? ?????? pageNum={} , keywords={}", pageNum, keywords);
        PageInfo<User> page = userService.findUserByPage(pageNum, keywords);
        map.put("page", page);
        map.put("keywords", keywords);
        return "view/user/user_list_page";
    }

    @DeleteMapping("/view/user/delete")
    public String deleteUser(@RequestParam String userId) {
        log.info("#delete user = {}", userId);
        userService.deleteUser(userId);
        return "view/user/user_list";
    }

    @PostMapping("/user/user_state")
    @ResponseBody
    public int userChangeState(@RequestParam String userid) {
        log.info("#user state ?????? = {}", userid);
        userService.updateStatus(userid);
        User user = userService.findUserById2(userid);
        int state = user.getStatus();
        return state;
    }

    @PostMapping("/view/user/search")
    // required = false??? ????????? ???????????? ????????? ?????? ?????? ?????? ?????? ?????? ????????? ???.
    public String search(@RequestParam(value="searchName", required = false) String searchName,
                         @RequestParam(value="searchPhone", required = false) String searchPhone,
                         @RequestParam(value="searchCompany", required = false) String searchCompany,
                         @RequestParam(value="searchRole", required = false) String searchRole,
                         @RequestParam(value = "pageNum", required = false) Integer pageNum, ModelMap map){
        // user_list_page ??????  (page<T> :: Page.class)
        Page<UserListPojo> page = userService.searchUsers(pageNum, searchName, searchPhone, searchCompany, searchRole);
        map.put("page", page);
        return "view/user/user_list";
    }
}
