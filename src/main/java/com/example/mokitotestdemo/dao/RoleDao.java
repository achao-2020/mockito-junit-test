package com.example.mokitotestdemo.dao;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mokitotestdemo.dto.RoleDTO;
import com.example.mokitotestdemo.entity.Role;
import com.example.mokitotestdemo.mapper.RoleMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author licc3
 * @date 2023-2-16 14:16
 */
@Repository
public class RoleDao extends ServiceImpl<RoleMapper, Role> {
    public RoleDTO getByCode(String roleCode, Long sysId) {
        LambdaQueryWrapper<Role> wrapper = Wrappers.<Role>lambdaQuery().eq(Role::getRoleCode, roleCode).eq(Role::getSysId, sysId);
        Role role = baseMapper.selectOne(wrapper);
        if (role == null) {
            return null;
        }
        return BeanUtil.copyProperties(role, RoleDTO.class);
    }

    public RoleDTO getByName(String roleName, Long sysId) {
        LambdaQueryWrapper<Role> wrapper = Wrappers.<Role>lambdaQuery().eq(Role::getRoleName, roleName).eq(Role::getSysId, sysId);
        Role role = baseMapper.selectOne(wrapper);
        if (role == null) {
            return null;
        }
        return BeanUtil.copyProperties(role, RoleDTO.class);
    }

    public Page<Role> pageManagerRoles(Long size, Long page, List<Long> sysIds) {
        Page<Role> pageRole = new Page<>();
        pageRole.setCurrent(page);
        pageRole.setSize(size);
         return baseMapper.selectPage(pageRole, Wrappers.<Role>lambdaQuery().in(Role::getSysId, sysIds));
    }
}