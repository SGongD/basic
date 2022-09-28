package com.basicit.web.controller;

import com.basicit.framework.datasource.PageInfo;
import com.basicit.model.auth.User;
import com.basicit.service.auth.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @GetMapping("view/user/user_list")
    public String user_list(ModelMap usermap) {
        PageInfo<User> upage = userService.findUserByPage(null, null);
        log.info("pppppage = {}", upage);
        usermap.put("upage", upage);
        return "view/user/user_list";
    }

    @PostMapping("/user/user_list_page")
    public String user_list_page(@RequestParam(value="keywords", required = false) String keywords, @RequestParam(value = "pageNum", required = false) Integer pageNum, ModelMap map) {
        log.info("#user pagination 쿼리 뉴스 pageNum={} , keywords={}", pageNum, keywords);
        PageInfo<User> upage = userService.findUserByPage(pageNum, keywords);
        map.put("upage", upage);
        map.put("keywords", keywords);
        return "view/user/user_list_page";
    }
}
