package com.sophia.cake.service.impl;

import com.sophia.cake.entity.po.Material;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author lizhe
 * @date 2020-04-01 21:37
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
class MaterialServiceTest {
    @Autowired
    private MaterialService service;

    @Test
    void queryMaterial() {
    }

    @Test
    void query() {
    }

    @Test
    void queryName() {
    }

    @Test
    void add() {
        Material material = new Material();
        material.setName("原材料");
        material.setCapacity(1000);
        material.setUnit("克");
        material.setPrice(52f);
        service.add(material);
    }

    @Test
    void delete() {
    }

    @Test
    void update() {
    }
}