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


/**
 * @author lizhe
 * @date 2020/3/25 10:14
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
class MaterialMapperTest {
    @Resource
    private MaterialMapper mapper;

    private Material material;

    @BeforeEach
    public void Before() {
        material = new Material();
        material.setName("test");
        material.setPrice(15f);
        material.setCapacity(1000);
        material.setPricePerUnit(material.getPrice() / material.getCapacity());
        material.setUnit("升");
    }


    @Test
    void findMaterials() {
        List<Material> materials = mapper.findMaterials();
        materials.forEach(material -> log.info("material = {}", material));
    }

    @Test
    void findMaterialById() {
        Material material = mapper.findMaterialById(19);
        log.info("material = {}", material);
    }

    @Test
    void addMaterial() {
        int result = mapper.addMaterial(material);
        log.info("id = {}", material.getId());
        log.info("result = {}", result);
    }

    @Test
    void updateMaterial() {
        material = mapper.findMaterialById(19);
        material.setUnit("开尔文");
        int result = mapper.updateMaterial(material);
        log.info("result = {}", result);
    }

    @Test
    void deleteMaterialByID() {
        int result = mapper.deleteMaterialById(1);
        log.info("result = {}", result);
    }

    @Test
    void findMVos() {
        List<MVo> mVos = mapper.findMVos();
        mVos.forEach(mVo -> log.info("mVo ={}", mVo));
    }
}