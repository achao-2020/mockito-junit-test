package com.example.mokitotestdemo.service;

import com.example.mokitotestdemo.dao.RoleDao;
import com.example.mokitotestdemo.dto.RoleDTO;
import com.example.mokitotestdemo.dto.RoleUpdateDTO;
import com.example.mokitotestdemo.dto.SystemDTO;
import com.example.mokitotestdemo.entity.System;
import com.example.mokitotestdemo.exception.BusinessException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * squaretest插件生成的单元测试，并修改模拟数据，用例断言
 * @author licc3
 * @date 2023-2-16 18:04
 */
@RunWith(MockitoJUnitRunner.class)
public class RoleServiceManualTest {

    @Mock
    private SystemService mockSystemService;

    @Mock
    private RoleDao mockRoleDao;

    @InjectMocks
    private RoleService roleService;

    @Test
    public void updateSucceed() {
        // 构造目标方法入参
        RoleUpdateDTO updateDTO = new RoleUpdateDTO();
        updateDTO.setId(1L);
        updateDTO.setRoleCode("roleCode");
        updateDTO.setRoleName("roleName");
        updateDTO.setSysId(1L);

        // 构造依赖方法返回值
        SystemDTO systemDTO = new SystemDTO();
        systemDTO.setId(1L);
        systemDTO.setSysName("");
        systemDTO.setSysCode("");

        // 构造数据库查询返回值
        RoleDTO codeRole = new RoleDTO();
        codeRole.setId(1L);
        codeRole.setRoleCode("");
        codeRole.setRoleName("");
        codeRole.setSysId(1L);

        // 构造数据库查询返回值
        RoleDTO nameRole = new RoleDTO();
        nameRole.setId(1L);
        nameRole.setRoleCode("");
        nameRole.setRoleName("");
        nameRole.setSysId(0L);

        // mock依赖方法返回结果
        when(mockSystemService.getById(1L)).thenReturn(systemDTO);
        when(mockRoleDao.getByCode("roleCode", 1L)).thenReturn(codeRole);
        when(mockRoleDao.getByName("roleName", 1L)).thenReturn(nameRole);

        roleService.update(updateDTO);

        // 验证方法执行次数是否满足期望
        verify(mockSystemService, times(1)).getById(1L);
        verify(mockRoleDao, times(1)).getByCode("roleCode", 1L);
        verify(mockRoleDao, times(1)).getByName("roleName", 1L);

    }

    @Test
    public void updateSucceedSystemNotFound() {
        // 构造目标方法入参
        RoleUpdateDTO updateDTO = new RoleUpdateDTO();
        updateDTO.setId(1L);
        updateDTO.setRoleCode("roleCode");
        updateDTO.setRoleName("roleName");
        updateDTO.setSysId(1L);
//        // 构造依赖方法返回值
//        SystemDTO systemDTO = new SystemDTO();
//        systemDTO.setId(1L);
//        systemDTO.setSysName("");
//        systemDTO.setSysCode("");
//
//        // 构造数据库查询返回值
//        RoleDTO codeRole = new RoleDTO();
//        codeRole.setId(1L);
//        codeRole.setRoleCode("");
//        codeRole.setRoleName("");
//        codeRole.setSysId(1L);
//
//        // 构造数据库查询返回值
//        RoleDTO nameRole = new RoleDTO();
//        nameRole.setId(1L);
//        nameRole.setRoleCode("");
//        nameRole.setRoleName("");
//        nameRole.setSysId(0L);

        // mock依赖方法返回结果
        when(mockSystemService.getById(1L)).thenReturn(null);
//        when(mockRoleDao.getByCode("roleCode", 1L)).thenReturn(codeRole);
//        when(mockRoleDao.getByName("roleName", 1L)).thenReturn(nameRole);
        try {
            roleService.update(updateDTO);
        } catch (BusinessException e) {
            assert "系统不存在！".equals(e.getMessage());
        }


        // 验证方法执行次数是否满足期望
        verify(mockSystemService, times(1)).getById(1L);
        verify(mockRoleDao, times(0)).getByCode("roleCode", 1L);
        verify(mockRoleDao, times(0)).getByName("roleName", 1L);

    }
}