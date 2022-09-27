package com.basicit.service.simple.impl;

import com.basicit.framework.constant.Constants;
import com.basicit.framework.datasource.DataSourceTagger;
import com.basicit.framework.datasource.PageInfo;
import com.basicit.framework.exception.BusinessException;
import com.basicit.framework.pk.FactoryAboutKey;
import com.basicit.framework.pk.TableEnum;
import com.basicit.mapper.simple.NewsMapper;
import com.basicit.model.simple.News;
import com.basicit.service.simple.NewsService;
import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;

/**
 * @author Crackers
 * @Description 뉴스 인터페이스 구현 클래스
 * @date Mar 16, 2017 5:19:24 PM
 */
@Service
public class NewsServiceImpl implements NewsService {

    private static final Logger log = LoggerFactory.getLogger(NewsServiceImpl.class);

    @Autowired
    private NewsMapper newsMapper;

    //@DS(DataSourceTagger.DB2)
    @Transactional(rollbackFor = BusinessException.class)
    @Override
    public boolean addNews(News news) {
        if (news != null) {
            news.setId(FactoryAboutKey.getPK(TableEnum.T_NEWS));
            news.setCreateTime(Calendar.getInstance().getTime());
            int flag = newsMapper.insert(news);
            if (StringUtils.equals(news.getTitle(), "a")) {
                throw new BusinessException("001", "트랜잭션 역추적 테스트");
            }
            return flag == 1;
        } else {
            return false;
        }
    }

    @Override
    public boolean editNews(News news) {
        if (news != null && StringUtils.isNotBlank(news.getId())) {
            int flag = newsMapper.updateById(news);
            return flag == 1;
        } else {
            return false;
        }
    }

    @Override
    public News findNewsById(String id) {
        if (StringUtils.isBlank(id)) {
            return null;
        } else {
            return newsMapper.selectById(id);
        }
    }

    @Override
    public List<News> findNewsByKeywords(String keywords) {
        //기본 데이터베이스
        log.info("# 기본 데이터베이스 쿼리");
        return newsMapper.findNewsByKeywords(keywords);
    }

    @DS(DataSourceTagger.DB1)
    @Override
    public PageInfo<News> findNewsByPage1(Integer pageNum, String keywords) {
        // 데이터 베이스1
        PageInfo<News> resultPage = findByPage(pageNum, keywords);
        log.info("# 데이터 베이스1 resultPage={}", resultPage);
        return resultPage;
    }


    @DS(DataSourceTagger.DB2)
    @Override
    public PageInfo<News> findNewsByPage2(Integer pageNum, String keywords) {
        // 데이터 베이스2
        PageInfo<News> resultPage = findByPage(pageNum, keywords);
        log.info("# 데이터 베이스2 resultPage={}", resultPage);
        return resultPage;
    }

    @Override
    public PageInfo<News> findNewsByPage(Integer pageNum, String keywords) {
        PageInfo<News> resultPage = findByPage(pageNum, keywords);
        log.info("# 기본 데이터베이스 resultPage={}", resultPage);
        return resultPage;
    }

    private PageInfo<News> findByPage(Integer pageNum, String keywords) {
        log.debug("# pageNum={},keywords={}", pageNum, keywords);
        if (pageNum == null) {
            pageNum = 1;
        }
        PageInfo<News> page = new PageInfo<>(pageNum, Constants.PAGE_SIZE);
        return newsMapper.findNewsByPage(page, keywords);
    }

}
