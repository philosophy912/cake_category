package com.sophia.cake.service.impl;

import com.philosophy.base.common.Pair;
import com.philosophy.base.entity.EnvData;
import com.philosophy.base.util.NumericUtils;
import com.sophia.cake.entity.bo.EntityBo;
import com.sophia.cake.entity.bo.NameBo;
import com.sophia.cake.entity.po.Material;
import com.sophia.cake.entity.vo.MVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


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
        EntityBo entityBo = new EntityBo();
        Material material = service.query(entityBo).getFirst().get(0);
        log.debug("material is {}", material);
        float price = NumericUtils.randomFloat(10, 100);
        log.debug("price is {}", price);
        material.setPrice(price);
        assertDoesNotThrow(() -> service.update(material));
    }

    @Test
    void queryMaterial() {
        List<MVo> mVos = service.queryMaterial();
        assertTrue(mVos.size() > 10);
    }

    @Test
    void query() {
        EntityBo entityBo = new EntityBo();
        Pair<List<Material>, EnvData> query = service.query(entityBo);
        assertTrue(query.getFirst().size() > 10);
    }

    @Test
    void queryName() {
        NameBo nameBo = new NameBo();
        nameBo.setName("糖");
        Pair<List<Material>, EnvData> query = service.queryName(nameBo);
        assertTrue(query.getFirst().size() > 2);
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