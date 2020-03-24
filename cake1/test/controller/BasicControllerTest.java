package com.sophia.cake.controller;

import com.sophia.cake.CakeApplication;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author lizhe
 * @date 2020-03-19 22:42
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CakeApplication.class)
@Slf4j
class BasicControllerTest {
    @Autowired
    private BasicController basicController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(basicController).build();
    }


    @SneakyThrows
    @Test
    void query() {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/basic/query")).andReturn();
        log.info("response = {}", mvcResult.getResponse().getContentAsString());
    }

    @Test
    void add() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}