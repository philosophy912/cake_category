package com.sophia.cake.entity;


import com.sophia.cake.CakeApplication;
import com.sophia.cake.dao.MaterialDao;
import com.sophia.cake.dao.db.JuniorDao;
import com.sophia.cake.dao.db.JuniorFormulaDao;
import com.sophia.cake.dao.db.SeniorDao;
import com.sophia.cake.entity.db.Junior;
import com.sophia.cake.entity.db.JuniorFormula;
import com.sophia.cake.entity.db.Senior;
import com.sophia.cake.service.db.JuniorFormulaService;
import com.sophia.cake.service.db.JuniorService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

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
    @Autowired
    private JuniorDao juniorDao;
    @Autowired
    private SeniorDao seniorDao;
    @Autowired
    private JuniorService juniorService;
    @Autowired
    private JuniorFormulaService juniorFormulaService;


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
        Junior junior = new Junior();
        junior.setName("原材料");
        junior.setCapacityType("克");
        junior.setCapacity(22700);
        junior.setPrice(165.0f);
        junior.update();
        juniorDao.saveAndFlush(junior);

        JuniorFormula juniorFormula = new JuniorFormula();
        juniorFormula.setCount(500f);
        juniorFormula.setJunior(junior);
        juniorFormula.update();
        // juniorFormulaDao.saveAndFlush(juniorFormula);

        Set<JuniorFormula> juniorFormulaSet = new HashSet<>();
        juniorFormulaSet.add(juniorFormula);

        Senior senior = new Senior();
        senior.setName("中级产品");
        senior.setJuniorFormulas(juniorFormulaSet);
        senior.update();
        seniorDao.saveAndFlush(senior);

    }


}
