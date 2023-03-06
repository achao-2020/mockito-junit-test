package com.example.mokitotestdemo.service;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mokitotestdemo.dao.RoleDao;
import com.example.mokitotestdemo.dto.RoleDTO;
import com.example.mokitotestdemo.dto.RoleUpdateDTO;
import com.example.mokitotestdemo.dto.SystemDTO;
import com.example.mokitotestdemo.entity.Role;
import com.example.mokitotestdemo.entity.System;
import com.example.mokitotestdemo.exception.BusinessException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author licc3
* @description 针对表【role】的数据库操作Service
* @createDate 2023-02-16 14:14:13
*/
@Service
public class RoleService {

    @Resource
    private RoleDao roleDao;

    @Resource
    private SystemService systemService;

    /**
     *
     * @param updateDTO
     */
    public void update(RoleUpdateDTO updateDTO) {
        SystemDTO systemDTO = systemService.getById(updateDTO.getSysId());
        if (systemDTO == null) {
            throw new BusinessException("系统不存在！");
        }

        RoleDTO codeRole = roleDao.getByCode(updateDTO.getRoleCode(), updateDTO.getSysId());
        if (codeRole != null && !codeRole.getId().equals(updateDTO.getId())) {
            throw new BusinessException("角色编码已存在" + codeRole.getRoleCode());
        }
        RoleDTO nameRole = roleDao.getByName(updateDTO.getRoleName(), updateDTO.getSysId());
        if (nameRole != null && !nameRole.getId().equals(updateDTO.getId())) {
            throw new BusinessException("角色名称已存在" + nameRole.getRoleName());
        }

        roleDao.updateById(BeanUtil.copyProperties(updateDTO, Role.class));
    }

    public void add(Role role) {
        roleDao.save(role);
    }

    public Page<Role> managerRoles(Long roleId, Long size, Long page ) {
        Role role = roleDao.getById(roleId);
        RoleDTO roleDTO = BeanUtil.toBean(role, RoleDTO.class);
        if (roleDTO == null) {
            throw new BusinessException("角色不存在!");
        }
        List<System> systems = systemService.managerSys(role);
        List<Long> sysIds = systems.stream().map(System::getId).collect(Collectors.toList());
        return this.roleDao.pageManagerRoles(size, page, sysIds);
    }
}
