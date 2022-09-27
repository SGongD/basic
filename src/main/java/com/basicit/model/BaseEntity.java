package com.basicit.model;

import java.io.Serializable;


/**
 * @param <E>
 * @author Crackers
 * @Description 엔터티 기본 클래스
 * @date Mar 16, 2017 3:25:15 PM
 */
public interface BaseEntity<E extends Serializable> extends Serializable {

    /**
     * 기본 키
     *
     * @return 기본 키 데이터 유형
     */
    E getId();

}
