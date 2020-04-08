package com.sophia.cake.mapper;

import com.sophia.cake.entity.po.Material;
import com.sophia.cake.entity.vo.MVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author lizhe
 * @date 2020-04-05 22:45
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class MaterialMapperTest {
    @Resource
    private MaterialMapper mapper;

    private Material material;

    @BeforeEach
    void setUp() {
        material = new Material();
        material.setName("test");
        material.setCapacity(1000);
        material.setPrice(150f);
        material.setPricePerUnit(material.getPrice() / material.getCapacity());
        material.setUnit("升");
    }


    @Test
    void findMaterialById() {
        Material material = mapper.findMaterialById(1);
        assertNotNull(material);
    }

    @Test
    void findMVos() {
        List<MVo> mVos = mapper.findMVos();
        assertTrue(mVos.size() > 10);

    }

    @Test
    void findMaterials() {
        List<Material> materials = mapper.findMaterials();
        assertTrue(materials.size() > 10);
    }

    @Test
    void findMaterialsByName() {
        List<Material> materials = mapper.findMaterialsByName("%糖%");
        assertTrue(materials.size() > 3);
    }

    @Test
    void addMaterial() {
        int result = mapper.addMaterial(material);
        assertEquals(1, result);
    }

    @Test
    void deleteMaterialById() {
        int result = mapper.deleteMaterialById(120);
        assertEquals(0, result);
    }

    @Test
    void updateMaterial() {
        Material material = mapper.findMaterialById(1);
        if (material.getUnit().equalsIgnoreCase("克")) {
            material.setUnit("毫升");
        } else {
            material.setUnit("克");
        }
        int result = mapper.updateMaterial(material);
        assertEquals(1, result);
    }

    @Test
    void findMaterialCount() {
        int count = mapper.findMaterialCount();
        log.debug("count = {}", count);
        assertTrue(count > 2);
    }

    @Test
    void findPageMaterials() {
        List<Material> materials = mapper.findPageMaterials(0, 5);
        assertEquals(5, materials.size());
    }

    @Test
    void findPageMaterialsByName() {
        List<Material> materials = mapper.findPageMaterialsByName("%糖%", 0, 1);
        assertEquals(1, materials.size());
    }


    @Test
    void findMaterialByNameCount() {
        int count = mapper.findMaterialByNameCount("%糖%");
        assertTrue(count > 1);
    }
}