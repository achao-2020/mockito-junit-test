package com.example.mokitotestdemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.mokitotestdemo.dto.RoleUpdateDTO;
import com.example.mokitotestdemo.global.Result;
import lombok.Getter;
import lombok.Setter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;

/**
 * 从controller-service-dao的集成测试
 * @author licc3
 * @date 2023-2-16 15:39
 */
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RoleControllerIntegratedTest {
    @Resource
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Getter
    @Setter
    private MockHttpServletRequest request;

    @Getter
    @Setter
    private MockHttpServletResponse response;

    @Getter
    @Setter
    private String uri;

    @Getter@Setter
    private String jsonRequest;

    private String baseCode = "content-type text/plain;charset=utf-8";

    @Before
    public void setMockMvc() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(
                webApplicationContext).build();
        request = new MockHttpServletRequest();
        request.setCharacterEncoding("UTF-8");
        response = new MockHttpServletResponse();
    }

    public void setData(String uri,Object object){
        this.uri = uri;
        this.jsonRequest = JSONObject.toJSONString(object);
    }


    @Test
    public void testUpdate() throws Exception {
        // Setup
        final RoleUpdateDTO updateDTO = new RoleUpdateDTO();
        updateDTO.setId(1L);
        updateDTO.setRoleCode("roleCode");
        updateDTO.setRoleName("roleName");
        updateDTO.setSysId(1L);

        // Run the test
        setData("/role/update", updateDTO);

        MvcResult mvcResult = postAndReturn();
        assert mvcResult.getResponse().getStatus() == 200;
        // Verify the results
    }

    @Test
    public void testUpdateValidate() throws Exception {
        // Setup
        final RoleUpdateDTO updateDTO = new RoleUpdateDTO();
        updateDTO.setId(null);
        updateDTO.setRoleCode("roleCode");
        updateDTO.setRoleName("roleName");
        updateDTO.setSysId(1L);

        // Run the test
        setData("/role/update", updateDTO);

        MvcResult mvcResult = postAndReturn();
        assert JSONObject.parseObject(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8), Result.class).getMsg().contains("角色id不能为空");

    }

    private MvcResult postAndReturn() throws Exception {
        return this.mockMvc.perform(MockMvcRequestBuilders.post(this.getUri())
                .contentType(MediaType.APPLICATION_JSON).content(jsonRequest==null ? "": jsonRequest)).andReturn();
    }
}