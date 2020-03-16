package com.sophia.cake.mapper;

import com.sophia.cake.CakeApplication;
import com.sophia.cake.entity.Material;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author lizhe
 * @date 2020/3/16 12:27
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CakeApplication.class)
@Slf4j
class MaterialMapperTest {
    @Resource
    private MaterialMapper materialMapper;

    @Test
    void findAll() {
        List<Material> materialList = materialMapper.findAll();
        log.debug("size is {}", materialList.size());
//        assertEquals(materialList.size(), 240);
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

    @Test
    void findById() {
        Material material = materialMapper.findById(5);
        log.info("material is {}", material);
        assertEquals(35, material.getCapacity());
    }

    @Test
    void findByName() {
        Material material = materialMapper.findByName("蛋白");
        log.info("material = {}", material);
        assertEquals(5, material.getName());
    }
}