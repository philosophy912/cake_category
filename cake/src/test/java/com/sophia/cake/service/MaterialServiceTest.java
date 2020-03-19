package com.sophia.cake.service;

import com.sophia.cake.CakeApplication;
import com.sophia.cake.entity.Material;
import com.sophia.cake.service.impl.MaterialService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author lizhe
 * @date 2020/3/12 16:14
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CakeApplication.class)
class MaterialServiceTest {

    @Autowired
    private MaterialService materialService;

    @Test
    void findAll() {
        List<Material> materialList = materialService.findAll();
        Assert.assertEquals(0, materialList.size());
    }

    @Test
    void add() {
    }

    @Test
    void delete() {
    }

    @Test
    void update() {
    }
}