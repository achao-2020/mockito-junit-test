package com.example.mokitotestdemo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mokitotestdemo.dto.RoleUpdateDTO;
import com.example.mokitotestdemo.entity.Role;
import com.example.mokitotestdemo.service.RoleService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author licc3
 * @date 2023-2-16 14:17
 */
@RestController
@RequestMapping("/role")
public class RoleController {
    @Resource
    private RoleService roleService;

    @PostMapping("/update")
    public void update(@RequestBody @Validated RoleUpdateDTO updateDTO) {
        roleService.update(updateDTO);
    }

    @GetMapping("/managerRoles")
    public Page<Role> managerRoles(@RequestParam Long roleId, @RequestParam Long size, @RequestParam Long page) {
        return roleService.managerRoles(roleId, size, page);
    }
}