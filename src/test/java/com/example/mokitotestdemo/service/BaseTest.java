package com.example.mokitotestdemo.service;

import com.example.mokitotestdemo.dao.RoleDao;
import com.example.mokitotestdemo.dao.SystemDao;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 公用父类，供单元测试类实现
 * 需要依赖的bean统一定义在父类属性中
 */
@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringRunner.class)
public class BaseTest {
    // @Spy注解表示这个类会注入到@InjectMocks，默认调用真正的方法
    @Spy
    @InjectMocks
    protected RoleDao mockRoleDao;
    @Spy
    @InjectMocks
    protected SystemDao mockSystemDao;
    @Spy
    @InjectMocks
    protected SystemService mockSystemService;

}