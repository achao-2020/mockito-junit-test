package com.example.mokitotestdemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName role
 */
@TableName(value ="role")
@Data
public class Role implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    private String roleCode;

    /**
     * 
     */
    private String roleName;

    private Integer roleType;

    /**
     * 
     */
    private Long sysId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}