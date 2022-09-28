package com.basicit.web.controller;

import com.basicit.framework.datasource.PageInfo;
import com.basicit.model.simple.News;
import com.basicit.service.simple.NewsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Crackers
 * @Description 뉴스 예
 * @date Mar 16, 2017 3:58:01 PM
 */
@Controller
public class NewsController {

    private static final Logger log = LoggerFactory.getLogger(NewsController.class);

    @Autowired
    private NewsService newsService;

    /**
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    /**
     * @return
     * @Description 등록 페이지
     */
    @GetMapping("/news/add")
    public String add() {
        log.info("# 출시 소식 페이지로 이동");
        return "view/news/add";
    }

    /**
     * ajax 뉴스 저장
     *
     * @param news 뉴스 엔터티
     * @return 처리 결과
     */
    @PostMapping("/news/add")
    @ResponseBody
    public Map<String, String> add(@ModelAttribute("newsForm") News news) {
        boolean flag = newsService.addNews(news);
        return getResultMap(flag);
    }

    private Map<String, String> getResultMap(boolean flag) {
        Map<String, String> result = new HashMap<>();
        if (flag) {
            result.put("status", "1");
            result.put("msg", "성공적으로 게시됨");
        } else {
            result.put("status", "0");
            result.put("msg", "게시 실패");
        }
        return result;
    }

    /**
     * @return
     * @Description ajax 뉴스 상세
     * @author Crackers
     */
    @GetMapping("/news/load/{id}")
    public String load(@PathVariable String id, ModelMap map) {
        log.info("# ajax뉴스 객체 로드");
        News news = newsService.findNewsById(id);
        map.addAttribute("news", news);
        return "view/news/edit_form";
    }

    /**
     * @param news
     * @return
     * @Description ajax 뉴스 수정
     * @author Crackers
     */
    @PostMapping("/news/edit")
    @ResponseBody
    public Map<String, String> edit(@ModelAttribute("newsForm") News news) {
        boolean flag = newsService.editNews(news);
        return getResultMap(flag);
    }

    @GetMapping("/news/list")
    public String list(ModelMap map) {
        PageInfo<News> page = newsService.findNewsByPage(null, null);
        log.info("jjjjpage = {}", page);
        map.put("page", page);
        return "view/news/list";
    }

    /**
     ** 
     ** 페이지수 관련 Mapping
     */
    @PostMapping("/news/list_page")
    public String list_page(@RequestParam(value = "keywords", required = false) String keywords, @RequestParam(value = "pageNum", required = false) Integer pageNum, ModelMap map) {
        log.info("#pagination 쿼리 뉴스 pageNum={} , keywords={}", pageNum, keywords);
        PageInfo<News> page = newsService.findNewsByPage(pageNum, keywords);
        map.put("page", page);
        map.put("keywords", keywords);
        return "view/news/list_page";
    }

    @GetMapping("/news/list1")
    public String list1(ModelMap map) {
        log.info("#페이징 쿼리 데이터베이스1");
        PageInfo<News> page = newsService.findNewsByPage1(null, null);
        map.put("page", page);
        return "view/news/list1";
    }

    @PostMapping("/news/list_page1")
    public String list_page1(@RequestParam(value = "keywords", required = false) String keywords, @RequestParam(value = "pageNum", required = false) Integer pageNum, ModelMap map) {
        log.info("#페이징 쿼리 데이터베이스2 pageNum={} , keywords={}", pageNum, keywords);
        PageInfo<News> page = newsService.findNewsByPage1(pageNum, keywords);
        map.put("page", page);
        map.put("keywords", keywords);
        return "view/news/list_page1";
    }

    @GetMapping("/news/list2")
    public String list2(ModelMap map) {
        log.info("#페이징 쿼리 데이터베이스2");
        PageInfo<News> page = newsService.findNewsByPage2(null, null);
        map.put("page", page);
        return "view/news/list2";
    }

    @PostMapping("/news/list_page2")
    public String list_page2(@RequestParam(value = "keywords", required = false) String keywords, @RequestParam(value = "pageNum", required = false) Integer pageNum, ModelMap map) {
        log.info("#페이징 쿼리 데이터베이스2 pageNum={} , keywords={}", pageNum, keywords);
        PageInfo<News> page = newsService.findNewsByPage2(pageNum, keywords);
        map.put("page", page);
        map.put("keywords", keywords);
        return "view/news/list_page2";
    }

}
