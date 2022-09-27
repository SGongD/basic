package com.basicit.service.simple;

import com.basicit.framework.datasource.PageInfo;
import com.basicit.model.simple.News;

import java.util.List;

/**
 * @author Crackers
 * @Description 뉴스 인터페이스 클래스
 * @date Mar 16, 2017 5:19:14 PM
 */
public interface NewsService {

    boolean addNews(News news);

    boolean editNews(News news);

    News findNewsById(String newsId);

    List<News> findNewsByKeywords(String keywords);

    PageInfo<News> findNewsByPage(Integer pageNum, String keywords);

    PageInfo<News> findNewsByPage1(Integer pageNum, String keywords);

    PageInfo<News> findNewsByPage2(Integer pageNum, String keywords);

}
