package com.yangyh.chapter.web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class UserControllerTest {

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new UserController()).build();
    }

    @Test
    public void testUserController() throws Exception {

        //测试userController
        RequestBuilder requestBuilder = null;

        //1.get查询一下user列表，应该为空
        requestBuilder = get("/user/");
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().string("[]"));

        //2.post提交一个user
        requestBuilder = post("/user/")
                .param("id", "1")
                .param("name", "杨永欢")
                .param("age", "20");

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().string("success"));

        //3.get查询一下user列表，应该有刚才插入的数据
        requestBuilder = get("/user/");
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().string("[{\"id\":1,\"name\":\"杨永欢\",\"age\":20}]"));

        //4.put提交修改id=1的user信息
        requestBuilder = put("/user/1")
                .param("name", "杨永欢test")
                .param("age", "21");

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().string("success"));

        //5.get查询id=1的user信息
        requestBuilder = get("/user/1");
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().string("{\"id\":1,\"name\":\"杨永欢test\",\"age\":21}"));

        //6.delete删除id=1的user信息
        requestBuilder = delete("/user/1");
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().string("success"));

        //7.get查询一下user列表，为空
        requestBuilder = get("/user/");
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().string("[]"));

    }
}