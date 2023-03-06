package com.example.mokitotestdemo.service;

import cn.hutool.core.bean.BeanUtil;
import com.example.mokitotestdemo.dao.RoleDao;
import com.example.mokitotestdemo.dto.RoleDTO;
import com.example.mokitotestdemo.dto.RoleUpdateDTO;
import com.example.mokitotestdemo.dto.SystemDTO;
import com.example.mokitotestdemo.entity.Role;
import com.example.mokitotestdemo.exception.BusinessException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class RoleServiceTest extends BaseTest {

    @InjectMocks
    private RoleService roleServiceUnderTest;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void testUpdate() {
        // 构造目标方法入参
        final RoleUpdateDTO updateDTO = new RoleUpdateDTO();
        updateDTO.setId(0L);
        updateDTO.setRoleCode("roleCode");
        updateDTO.setRoleName("roleName");
        updateDTO.setSysId(0L);

        // 构造依赖方法返回值
        final SystemDTO systemDTO = new SystemDTO();
        systemDTO.setId(0L);
        systemDTO.setSysName("sysName");
        systemDTO.setSysCode("sysCode");

        // 构造数据库查询返回值
        final RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(0L);
        roleDTO.setRoleCode("roleCode");
        roleDTO.setRoleName("roleName");
        roleDTO.setSysId(0L);

        // 构造数据库查询返回值
        final RoleDTO roleDTO1 = new RoleDTO();
        roleDTO1.setId(0L);
        roleDTO1.setRoleCode("roleCode");
        roleDTO1.setRoleName("roleName");
        roleDTO1.setSysId(0L);
        // mock依赖方法返回结果
        doReturn(systemDTO).when(mockSystemService).getById(any());
        doReturn(roleDTO).when(mockRoleDao).getByCode(any(), any());
        doReturn(roleDTO1).when(mockRoleDao).getByName(any(), any());
        doReturn(false).when(mockRoleDao).updateById(any());

        // Run the test
        roleServiceUnderTest.update(updateDTO);

        // 验证方法执行次数是否满足期望
        verify(mockRoleDao, times(1)).updateById(any());
        verify(mockRoleDao, times(1)).getByCode("roleCode", 0L);
        verify(mockRoleDao, times(1)).getByName("roleName", 0L);
    }

    @Test(expected = BusinessException.class)
    public void testUpdate_SystemServiceReturnsNull() {
        // Setup
        final RoleUpdateDTO updateDTO = new RoleUpdateDTO();
        updateDTO.setId(0L);
        updateDTO.setRoleCode("roleCode");
        updateDTO.setRoleName("roleName");
        updateDTO.setSysId(0L);
        // 系统查询返回为空，报错
        doReturn(null).when(mockSystemService).getById(any());

        // Run the test
        try {
            roleServiceUnderTest.update(updateDTO);
        } catch (BusinessException e) {
            assertEquals("系统不存在！", e.getMessage());
            throw e;
        }
    }

    @Test
    public void testUpdate_roleCodeExisted() {
        // Setup
        final RoleUpdateDTO updateDTO = new RoleUpdateDTO();
        updateDTO.setId(0L);
        updateDTO.setRoleCode("roleCode");
        updateDTO.setRoleName("roleName");
        updateDTO.setSysId(0L);

        // Configure SystemService.getById(...).
        final SystemDTO systemDTO = new SystemDTO();
        systemDTO.setId(0L);
        systemDTO.setSysName("sysName");
        systemDTO.setSysCode("sysCode");

        RoleDTO roleCodeEntity = new RoleDTO();
        roleCodeEntity.setId(1L);
        roleCodeEntity.setRoleCode("roleCode");
        roleCodeEntity.setRoleName("roleName");
        roleCodeEntity.setSysId(0L);


        doReturn(systemDTO).when(mockSystemService).getById(any());
        doReturn(roleCodeEntity).when(mockRoleDao).getByCode(any(), any());

        // Run the test
        try {
            roleServiceUnderTest.update(updateDTO);
        } catch (BusinessException e) {
            assertEquals("角色编码已存在" + roleCodeEntity.getRoleCode(), e.getMessage());
        }
    }

    @Test
    @PrepareForTest(BeanUtil.class)
    public void testUpdate_Integrate_roleTypeNotFound() {
        // 新增角色
        final Role addRole = new Role();
        addRole.setId(0L);
        addRole.setRoleCode("");
        addRole.setRoleName("");
        addRole.setRoleType(3);
        addRole.setSysId(0L);

        // 模拟静态方法返回值
        RoleDTO staticRoleDTO = new RoleDTO();
        staticRoleDTO.setId(0L);
        staticRoleDTO.setRoleCode("");
        staticRoleDTO.setRoleName("");
        staticRoleDTO.setSysId(0L);

        Long roleId = 1L;
        Long size = 10L;
        Long page = 1L;

        doReturn(true).when(mockRoleDao).save(any());
        doReturn(addRole).when(mockRoleDao).getById(1L);
        PowerMockito.mockStatic(BeanUtil.class);
        PowerMockito.when(BeanUtil.toBean(addRole, RoleDTO.class)).thenReturn(staticRoleDTO);

        // Run the test
        roleServiceUnderTest.add(addRole);
        try {
            roleServiceUnderTest.managerRoles(roleId, size, page);
        } catch (BusinessException e) {
            assertEquals("没有找到对于的角色枚举类型,value=" + addRole.getRoleType(), e.getMessage());
        }
    }
}
