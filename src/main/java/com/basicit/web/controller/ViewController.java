package com.basicit.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 예시 페이지 controller
 *
 * @author Crackers
 * @date 2022/3/24 15:12
 */
@Controller
public class ViewController {

    private static final Logger log = LoggerFactory.getLogger(ViewController.class);

    @GetMapping("view/{submenu}/{menu}")
    public String setconfig(@PathVariable(value = "submenu") String submenu, @PathVariable(value = "menu") String menu) {
        log.info("# View loding view/{}/{} ", submenu, menu);
        return "view/" + submenu + "/" + menu;
    }
}
