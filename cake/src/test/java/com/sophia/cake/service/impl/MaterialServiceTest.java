package com.sophia.cake.service.impl;

import com.sophia.cake.entity.po.Material;
import com.sophia.cake.mapper.MaterialMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author lizhe
 * @date 2020/4/7 13:09
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
class MaterialServiceTest {

    @Resource
    private MaterialMapper mapper;
    @Autowired
    private MaterialService service;

    @Test
    void update() {
        Material material = mapper.findMaterialById(1);
        material.setPricePerUnit(10f);
        assertDoesNotThrow(() -> {
            service.update(material);
        });
    }
}