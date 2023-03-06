package com.example.mokitotestdemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.mokitotestdemo.dto.RoleUpdateDTO;
import com.example.mokitotestdemo.service.RoleService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 手动编写的controller单元测试
 *
 * @author licc3
 * @date 2023-2-16 15:57
 */
@RunWith(MockitoJUnitRunner.class)
public class RoleControllerManualTest {
    private MockMvc mockMvc;

    private String prefix;
    // mock代理依赖的类
    @Mock
    private RoleService mockRoleService;
    // 需要测试的类
    @InjectMocks
    private RoleController roleController;

    @Before
    public void setUp() {
        // 构造请求的模拟对象
        this.mockMvc = MockMvcBuilders.standaloneSetup(roleController).build();

        this.prefix = StringUtils.replace("/role", " ", "");
    }

    /**
     * 测试用例：请求，入参，状态
     *
     * @throws Exception
     */
    @Test
    public void updateTest() throws Exception {
        // 模拟请求参数
        final RoleUpdateDTO updateDTO = new RoleUpdateDTO();
        updateDTO.setId(1L);
        updateDTO.setRoleCode("roleCode");
        updateDTO.setRoleName("roleName");
        updateDTO.setSysId(1L);
        // mock依赖方法
        Mockito.doNothing().when(mockRoleService).update(any());
        // 模拟请求
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post(this.prefix + "/update").contentType(
                MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(updateDTO));
        // 判断http状态码200
        mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();
    }

    /**
     * 期望返回参数为空异常
     * @throws Exception
     */
    @Test
    public void updateTestValidate() throws Exception {
        final RoleUpdateDTO updateDTO = new RoleUpdateDTO();
        // 设置未null，检查valid是否生效
        updateDTO.setId(null);
        updateDTO.setRoleCode("roleCode");
        updateDTO.setRoleName("roleName");
        updateDTO.setSysId(1L);
        // mock依赖方法
        Mockito.doNothing().when(mockRoleService).update(any());
        // 模拟请求
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post(this.prefix + "/update").contentType(
                MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(updateDTO));
        // 判断http状态码400
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andExpect(status().is(400)).andReturn();
        Exception resolvedException = mvcResult.getResolvedException();
        // 这里没有经过统一异常处理和统一返回包装，所以抛出的错误在此处断言
        assert resolvedException instanceof MethodArgumentNotValidException;

    }
}