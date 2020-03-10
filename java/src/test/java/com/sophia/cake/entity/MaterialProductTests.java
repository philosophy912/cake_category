package com.sophia.cake.entity;


import com.philosophy.base.util.FilesUtils;
import com.sophia.cake.CakeApplication;
import com.sophia.cake.dao.MaterialDao;
import com.sophia.cake.dao.db.JuniorFormulaDao;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author lizhe
 * @date 2020-03-04 21:16
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CakeApplication.class)
@Slf4j
public class MaterialProductTests {

    @Autowired
    private MaterialDao materialDao;
    @Autowired
    private JuniorFormulaDao juniorFormulaDao;


    @Test
    public void add() throws IOException {
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
        for (int i = 0; i < 10; i++) {
            materialDao.add(material);
        }
    }

    @Test
    public void test() {
        Path path = Paths.get(FilesUtils.getCurrentPath() + File.separator + "db" + File.separator + "material.db");
        if (Files.exists(path)) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }

    }

}
