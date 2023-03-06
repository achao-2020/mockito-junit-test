package com.example.mokitotestdemo.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author licc3
 * @date 2023-2-20 09:49
 */
@Data
public class RoleVO {
    private Long id;

    /**
     *
     */
    private String roleCode;

    /**
     *
     */
    private String roleName;

    private String roleType;

    /**
     *
     */
    private Long sysId;
}