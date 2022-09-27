package com.basicit.web.shiro.vo;

import com.basicit.framework.constant.Constants;
import com.basicit.model.auth.Permission;

import java.util.ArrayList;
import java.util.List;


/**
 * 메뉴 테스트 데이터
 *
 * @author Crackers
 * @date 2022/3/15 11:03
 */
public enum PermissionEnumUtil {

    /**
     * 홈 관리
     */
    GLSY("대시보드", "fa-home", "glsy", null, "index", 1, 1),

    //-----------------------------------------------------------------------------------------------------
    /**
     * 역 뉴스
     */
    ZNXY("뉴스", null, "znxy", null, null, 1, 10),
    /**
     * 보도 자료
     */
    ZNXY_XWFB("등록", null, "znxy_xwfb", "znxy", "news/add", 2, 11),
    /**
     * 뉴스 목록
     */
    ZNXY_XWLB("목록", null, "znxy_xwlb", "znxy", "news/list", 2, 12),

    /**
     * 데이터 베이스1
     */
    ZNXY_SJK1("db1", null, "znxy_sjk1", "znxy", "news/list1", 2, 14),
    /**
     * 데이터 베이스2
     */
    ZNXY_SJK2("db2", null, "znxy_sjk2", "znxy", "news/list2", 2, 15)
    ;

    PermissionEnumUtil(String name, String cssClass, String key, String parentKey, String url, Integer lev, Integer sort) {
        this.name = name;
        this.cssClass = cssClass;
        this.key = key;
        this.lev = lev;
        this.sort = sort;
        this.url = url;
        this.hide = Constants.STATUS_VALID;
        this.parentKey = parentKey;
    }

    public static List<Permission> getPermissions() {
        List<Permission> list = new ArrayList<>();
        PermissionEnumUtil[] pers = PermissionEnumUtil.values();
        Permission per = null;
        for (PermissionEnumUtil p : pers) {
            per = new Permission();
            per.setName(p.getName());
            per.setCssClass(p.getCssClass());
            per.setSkey(p.getKey());
            per.setHide(p.getHide());
            per.setUrl(p.getUrl());
            per.setLev(p.getLev());
            per.setSort(p.getSort());
            per.setParentKey(p.getParentKey());
            list.add(per);
        }
        return list;
    }

    private String name;
    private String cssClass;
    private String key;
    private Integer hide;
    private String url;
    private Integer lev;
    private Integer sort;
    private String parentKey;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCssClass() {
        return cssClass;
    }

    public void setCssClass(String cssClass) {
        this.cssClass = cssClass;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getHide() {
        return hide;
    }

    public void setHide(Integer hide) {
        this.hide = hide;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getLev() {
        return lev;
    }

    public void setLev(Integer lev) {
        this.lev = lev;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getParentKey() {
        return parentKey;
    }

    public void setParentKey(String parentKey) {
        this.parentKey = parentKey;
    }

}
