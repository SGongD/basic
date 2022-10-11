package com.basicit.web.controller;

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
        // UserPwPojo 정보를 불러옴
        // existUser 생성 (UserDTO속성을 다 가져옴)
        User existUser = userService.findUserById2(pwPojo.getId());

        User user = new User();

        //
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
            result.put("msg", "성공적으로 게시됨");
        } else {
            result.put("status", "0");
            result.put("msg", "게시 실패");
        }
        return result;
    }


    // 사용자 수정 버튼을 누를 시 load
    @GetMapping("/user/load/{id}")
    // @PathVariable : 경로의 특정 위치 값이 고정되지 않고 달라질 때 사용하는 것 {id}
    public String load(@PathVariable String id, ModelMap map) {
        log.info("# ajax사용자 객체 업로드");
        // id값을 받아와 user 정보 값을 찾아서 대입
        UserDTO user = userService.findUserById(id);
        log.info("pageLoad :: UserDTO = " + user);
        // addAttribute: hashMap형태로 데이터를 key-value 꼴로 Return 해준다.
        // map이라는 것은 Key - Value 두 쌍으로 데이터를 보관하는 자료구조(Key는 유일한 값)
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
        log.info("#user pagination 쿼리 뉴스 pageNum={} , keywords={}", pageNum, keywords);
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
}
