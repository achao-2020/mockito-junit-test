package com.example.mokitotestdemo.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author licc3
 * @date 2023-2-16 14:18
 */
@Data
public class RoleUpdateDTO {

    @NotNull(message = "角色id不能为空")
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