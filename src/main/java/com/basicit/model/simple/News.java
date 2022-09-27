package com.basicit.model.simple;

import com.basicit.model.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;


/**
 * 뉴스 개체
 *
 * @author Crackers
 * @date 2022/3/15 14:10
 */
@Data
@TableName("t_news")
public class News implements BaseEntity<String> {

    private static final long serialVersionUID = 3624947930970250778L;

    @TableId("id")
    private String id;

    /**
     * 헤드라인
     */
    @TableField("title")
    private String title;

    /**
     * 뉴스 콘텐츠
     */
    @TableField("description")
    private String description;

    /**
     * 뉴스 주소
     */
    @TableField("address")
    private String address;

    /**
     * 뉴스의 시간
     */
    @TableField("news_time")
    private Date newsTime;

    /**
     * 보도 자료 시간
     */
    @TableField("create_time")
    private Date createTime;
}
