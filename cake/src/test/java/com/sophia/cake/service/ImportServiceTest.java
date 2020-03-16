package com.sophia.cake.service;

import com.philosophy.base.common.Triple;
import com.sophia.cake.CakeApplication;
import com.sophia.cake.entity.Basic;
import com.sophia.cake.entity.BasicFormula;
import com.sophia.cake.entity.Material;
import com.sophia.cake.entity.MaterialFormula;
import com.sophia.cake.entity.Middle;
import com.sophia.cake.mapper.BasicFormulaMapper;
import com.sophia.cake.mapper.BasicMapper;
import com.sophia.cake.mapper.MaterialFormulaMapper;
import com.sophia.cake.mapper.MaterialMapper;
import com.sophia.cake.mapper.MiddleMapper;
import com.sophia.cake.utils.EntityUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;


/**
 * @author lizhe
 * @date 2020-03-15 10:32
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CakeApplication.class)
@Slf4j
class ImportServiceTest {
    @Autowired
    private EntityUtils entityUtils;

    @Autowired
    private ImportService importService;
    @Resource
    private MaterialMapper materialMapper;
    @Resource
    private BasicMapper basicMapper;
    @Resource
    private MaterialFormulaMapper materialFormulaMapper;
    @Resource
    private BasicFormulaMapper basicFormulaMapper;
    @Resource
    private MiddleMapper middleMapper;

    private List<Material> materials;
    private List<Basic> basics;
    private List<Middle> middles;

    @BeforeEach
    public void before() {
        Triple<List<Material>, List<Basic>, List<Middle>> triple = importService.getEntity();
        materials = triple.getFirst();
        basics = triple.getSecond();
        middles = triple.getThird();
    }


    @Test
    public void addMaterials() {
        // 添加Materials
        log.info("size {}", materials.size());
        materials.forEach(material -> {
            entityUtils.update(material);
            materialMapper.add(material);
        });

    }

    @Test
    public void addBasic() {
        for (Basic basic : basics) {
            entityUtils.update(basic);
            // 添加到basic表并返回id
            // int basicId = basicMapper.add(basic);
            Basic search = basicMapper.findByName(basic.getName());
            log.info("basic is {}", search.getId());
            int basicId = search.getId();
            Set<MaterialFormula> materialFormulas = basic.getMaterialFormulaSet();
            for (MaterialFormula formula : materialFormulas) {
                log.info("formula is {}", formula);
                // 添加了MaterialFormula并返回ID
                int materialFormulaId = materialFormulaMapper.add(formula);
                log.debug("materialFormulaId is {}", materialFormulaId );
                materialFormulaMapper.updateBasic(materialFormulaId, basicId);
                String name = formula.getMaterial().getName();
                Material material = materialMapper.findByName(name);
                materialFormulaMapper.updateMaterial(materialFormulaId, material.getId());
            }
        }
    }

    @Test
    public void addBasicFormula() {
        for (Basic basic : basics) {
            entityUtils.update(basic);
            // 添加到basic表并返回id
            int basicId = basicMapper.add(basic);
            Set<MaterialFormula> materialFormulas = basic.getMaterialFormulaSet();
            for (MaterialFormula formula : materialFormulas) {
                // 添加了MaterialFormula并返回ID
                int materialFormulaId = materialFormulaMapper.add(formula);
                materialFormulaMapper.updateBasic(materialFormulaId, basicId);
                String name = formula.getMaterial().getName();
                Material material = materialMapper.findByName(name);
                materialFormulaMapper.updateMaterial(materialFormulaId, material.getId());
            }
        }
    }

    @Test
    public void addMiddle() {
        for (Middle middle : middles) {
            entityUtils.update(middle);
            // 添加Middle表并返回id
            int middleId = middleMapper.add(middle);
            Set<MaterialFormula> materialFormulas = middle.getMaterialFormulaSet();
            for (MaterialFormula formula : materialFormulas) {
                // 添加了MaterialFormula并返回ID
                int materialFormulaId = materialFormulaMapper.add(formula);
                materialFormulaMapper.updateMiddle(materialFormulaId, middleId);
            }
            Set<BasicFormula> basicFormulas = middle.getBasicFormulaSet();
            for (BasicFormula formula : basicFormulas) {
                // 添加了BasicFormula并返回ID
                int basicFormula = basicFormulaMapper.add(formula);
                basicFormulaMapper.updateMiddle(basicFormula, middleId);
            }
        }
    }


}