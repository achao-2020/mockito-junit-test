package com.example.mokitotestdemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName system
 */
@TableName(value ="`system`")
@Data
public class System implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    private String sysName;

    /**
     * 
     */
    private String sysCode;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}