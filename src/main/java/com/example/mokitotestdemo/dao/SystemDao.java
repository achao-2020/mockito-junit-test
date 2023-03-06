package com.example.mokitotestdemo.dao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mokitotestdemo.entity.System;
import com.example.mokitotestdemo.service.SystemService;
import com.example.mokitotestdemo.mapper.SystemMapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
* @author licc3
* @description 针对表【system】的数据库操作Service实现
* @createDate 2023-02-16 14:27:21
*/
@Repository
public class SystemDao extends ServiceImpl<SystemMapper, System> {

}




