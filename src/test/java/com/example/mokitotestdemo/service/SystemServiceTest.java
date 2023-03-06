package com.example.mokitotestdemo.service;

import com.example.mokitotestdemo.dao.SystemDao;
import com.example.mokitotestdemo.dto.SystemDTO;
import com.example.mokitotestdemo.entity.Role;
import com.example.mokitotestdemo.entity.System;
import com.example.mokitotestdemo.mapper.SystemMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.MockitoAnnotations.initMocks;

public class SystemServiceTest extends BaseTest {

    @Spy
    @InjectMocks
    protected SystemDao mockSystemDao;
    @Spy
    protected SystemMapper mockSystemMapper;

    @InjectMocks
    private SystemService systemServiceUnderTest;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void testGetById() {
        // Setup
        final SystemDTO expectedResult = new SystemDTO();
        expectedResult.setId(0L);
        expectedResult.setSysName("sysName");
        expectedResult.setSysCode("sysCode");

        // Configure SystemDao.getById(...).
        final System system = new System();
        system.setId(0L);
        system.setSysName("sysName");
        system.setSysCode("sysCode");
        doReturn(system).when(mockSystemDao).getById(any());

        // Run the test
        final SystemDTO result = systemServiceUnderTest.getById(0L);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetById_SystemDaoReturnsNull() {
        // Setup
        doReturn(null).when(mockSystemDao).getById(any());

        // Run the test
        final SystemDTO result = systemServiceUnderTest.getById(0L);

        // Verify the results
        assertNull(result);
    }

    @Test
    public void testManagerSys() {
        // Setup
        final Role role = new Role();
        role.setId(0L);
        role.setRoleCode("roleCode");
        role.setRoleName("roleName");
        role.setRoleType(0);
        role.setSysId(0L);

        final System system = new System();
        system.setId(0L);
        system.setSysName("sysName");
        system.setSysCode("sysCode");
        final List<System> expectedResult = Arrays.asList(system);

        // Configure SystemDao.list(...).
        final System system1 = new System();
        system1.setId(0L);
        system1.setSysName("sysName");
        system1.setSysCode("sysCode");
        final List<System> systems = Arrays.asList(system1);
        doReturn(systems).when(mockSystemDao).list();

        // Configure SystemDao.getById(...).
        final System system2 = new System();
        system2.setId(0L);
        system2.setSysName("sysName");
        system2.setSysCode("sysCode");
        doReturn(system2).when(mockSystemDao).getById(any());

        // Run the test
        final List<System> result = systemServiceUnderTest.managerSys(role);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testManagerSys_SystemDaoListReturnsNoItems() {
        // Setup
        final Role role = new Role();
        role.setId(0L);
        role.setRoleCode("roleCode");
        role.setRoleName("roleName");
        role.setRoleType(0);
        role.setSysId(0L);

        doReturn(Collections.emptyList()).when(mockSystemDao).list();

        // Run the test
        final List<System> result = systemServiceUnderTest.managerSys(role);

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }
}
