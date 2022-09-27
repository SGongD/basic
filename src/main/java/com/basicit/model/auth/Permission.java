package com.basicit.model.auth;

import com.basicit.model.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 메뉴 개체
 *
 * @author Crackers
 * @date 2022/3/15 14:13
 */
@Data
@TableName("t_sys_permission")
public class Permission implements BaseEntity<String> {

    private static final long serialVersionUID = -7141829387338999544L;

    /**
     * 기본 키
     */
    @TableId("id")
    private String id;

    /**
     * 메뉴 이름
     */
    @TableField("name")
    private String name;

    /**
     * 메뉴 스타일 아이콘 이름
     */
    @TableField("css_class")
    private String cssClass;

    /**
     * 메뉴 상대 URL
     */
    @TableField("url")
    private String url;

    /**
     * 메뉴 키
     */
    @TableField("skey")
    private String skey;

    /**
     * 상위 메뉴 KEY
     */
    @TableField("parent_key")
    private String parentKey;

    /**
     * 표시 여부：1=효율적인，0=유효하지 않은
     */
    @TableField("hide")
    private Integer hide;

    /**
     * 메뉴 레벨，최대 3개의 레벨만 가질 수 있습니다.
     */
    @TableField("lev")
    private Integer lev;

    /**
     * 메뉴 정렬
     */
    @TableField("sort")
    private Integer sort;

}
