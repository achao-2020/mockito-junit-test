package com.example.mokitotestdemo.service;

import cn.hutool.core.bean.BeanUtil;
import com.example.mokitotestdemo.dao.SystemDao;
import com.example.mokitotestdemo.dto.SystemDTO;
import com.example.mokitotestdemo.entity.Role;
import com.example.mokitotestdemo.entity.System;
import com.example.mokitotestdemo.enums.RoleType;
import com.example.mokitotestdemo.exception.BusinessException;
import com.example.mokitotestdemo.mapper.SystemMapper;
import com.example.mokitotestdemo.vo.SystemVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.annotation.Resources;
import java.util.Arrays;
import java.util.List;

/**
* @author licc3
* @description 针对表【system】的数据库操作Service
* @createDate 2023-02-16 14:27:21
*/
@Service
public class SystemService {
    @Resource
    private SystemDao systemDao;

    @Resource
    private SystemMapper systemMapper;

    /**
     * testCase:
     * {@link com.example.mokitotestdemo.service.SystemServiceTest#testGetById}
     * @param sysId
     * @return
     */
    public SystemDTO getById(Long sysId) {
        if (sysId == null) {
            return null;
        }
        System system = systemDao.getById(sysId);
        if (system == null) {
            return null;
        }
        return BeanUtil.copyProperties(system, SystemDTO.class);
    }

    public List<System> managerSys(Role role) {
        RoleType roleType = RoleType.valueOf(role.getRoleType());
        if (roleType == null) {
            throw new BusinessException("没有找到对于的角色枚举类型,value=" + role.getRoleType());
        }
        if (RoleType.SYS_INSPECTION.equals(roleType)) {
            return systemDao.list();
        } else {
            return Arrays.asList(systemDao.getById(role.getSysId()));
        }
    }
}
