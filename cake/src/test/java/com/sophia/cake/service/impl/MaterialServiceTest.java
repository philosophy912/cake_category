package com.sophia.cake.service.impl;

import com.sophia.cake.entity.po.Material;
import com.sophia.cake.entity.vo.MVo;
import com.sophia.cake.mapper.MaterialMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author lizhe
 * @date 2020/4/7 13:09
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
class MaterialServiceTest {

    @Autowired
    private MaterialService service;

    @Test
    void update() {
        Material material = service.query().get(0);
        material.setPricePerUnit(10f);
        assertDoesNotThrow(() -> service.update(material));
    }

    @Test
    void queryMaterial() {
        List<MVo> mVos = service.queryMaterial();
        assertTrue(mVos.size() > 10);
    }

    @Test
    void query() {
        List<Material> materials = service.query();
        assertTrue(materials.size() > 10);
    }

    @Test
    void queryName() {
        List<Material> materials = service.queryName("糖");
        assertTrue(materials.size() > 2);
    }

    @Test
    void add() {
        Material material = new Material();
        material.setName("测试");
        material.setCapacity(1000);
        material.setPrice(300f);
        material.setPricePerUnit(material.getCapacity() / material.getPrice());
        material.setUnit("克");
        assertDoesNotThrow(() -> service.add(material));
    }

    @Test
    void delete() {
        Material material = new Material();
        material.setId(120);
        assertThrows(RuntimeException.class, () -> service.delete(material));
    }
}