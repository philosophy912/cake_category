package com.sophia.cake.entity;


import com.sophia.cake.CakeApplication;
import com.sophia.cake.dao.MaterialDao;
import com.sophia.cake.dao.MaterialProductDao;
import com.sophia.cake.service.entity.MaterialProductService;
import com.sophia.cake.service.entity.MaterialService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author lizhe
 * @date 2020-03-04 21:16
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CakeApplication.class)
@Slf4j
public class MaterialProductTests {

    @Autowired
    public MaterialProductService materialProductService;
    @Autowired
    public MaterialService materialService;
    @Autowired
    public MaterialProductDao materialProductDao;
    @Autowired
    public MaterialDao materialDao;


    @Test
    public void add() {
        Material material = new Material();
        material.setName("美玫低筋面粉");
        material.setCapacityType("克");
        material.setCapacity(22700);
        material.setPrice(165.0f);
        material.setPricePerCapacity(0.0072687226f);
        MaterialProduct materialProduct = new MaterialProduct();
        materialProduct.setCount(120f);
        materialProduct.setTotalPrice(0.87224674f);
        materialProduct.setMaterial(material);
        log.info("materialProduct = {}", materialProduct);
        materialDao.saveAndFlush(material);
        materialProductDao.saveAndFlush(materialProduct);
    }

}
