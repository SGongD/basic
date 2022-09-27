package com.basicit;

import com.basicit.model.simple.News;
import com.basicit.service.simple.NewsService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;

@SpringBootTest
public class ApplicationTests {

    private static final Logger log = LoggerFactory.getLogger(ApplicationTests.class);

    @Autowired
    private NewsService newsService;

    @Test
    public void addNews() {
        log.info("# 생산 테스트 데이터");
        News news = null;
        for (int i = 1; i < 1001; i++) {
            news = new News();
            news.setTitle("db1_test_" + i);
            news.setDescription("db1_test_" + i);
            news.setAddress("db1_test_" + i);
            news.setNewsTime(Calendar.getInstance().getTime());
            newsService.addNews(news);
        }

    }

}
