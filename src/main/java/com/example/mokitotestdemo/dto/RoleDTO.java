package com.example.mokitotestdemo.dto;

import lombok.Data;

/**
 * @author licc3
 * @date 2023-2-16 14:44
 */
@Data
public class RoleDTO {
    private Long id;

    /**
     *
     */
    private String roleCode;

    /**
     *
     */
    private String roleName;

    /**
     *
     */
    private Long sysId;
}