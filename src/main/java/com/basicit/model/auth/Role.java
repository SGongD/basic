package com.basicit.model.auth;

import com.basicit.model.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


/**
 * 역할 개체
 *
 * @author Crackers
 * @date 2022/3/15 14:12
 */
@Data
@TableName("t_sys_role")
public class Role implements BaseEntity<String> {

    private static final long serialVersionUID = -6982490361440305761L;


    /**
     * 기본 키
     */
    @TableId("id")
    private String id;

    /**
     * 캐릭터 이름
     */
    @TableField("name")
    private String name;

    /**
     * 역할 코딩
     */
    @TableField("code")
    private String code;

    /**
     * 주목
     */
    @TableField("remark")
    private String remark;

}
