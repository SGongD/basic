package com.basicit.mapper.simple;

import com.basicit.framework.datasource.PageInfo;
import com.basicit.model.simple.News;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 뉴스 개체 Mapper
 * @author Crackers
 * @Description 뉴스 mapper
 * @date Mar 16, 2017 3:35:19 PM
 */
@Mapper
public interface NewsMapper extends BaseMapper<News> {

    List<News> findNewsByKeywords(@Param("keywords") String keywords);

    PageInfo<News> findNewsByPage(PageInfo<News> page, @Param("keywords") String keywords);

}
