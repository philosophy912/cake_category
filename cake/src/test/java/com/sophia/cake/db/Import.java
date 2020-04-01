package com.sophia.cake.db;

import com.sophia.cake.entity.po.Basic;
import com.sophia.cake.entity.po.BasicFormula;
import com.sophia.cake.entity.po.Material;
import com.sophia.cake.entity.po.MaterialFormula;
import com.sophia.cake.entity.po.Middle;
import com.sophia.cake.entity.vo.FormulaType;
import com.sophia.cake.entity.vo.FormulaVo;
import com.sophia.cake.mapper.BasicMapper;
import com.sophia.cake.mapper.FormulaMapper;
import com.sophia.cake.mapper.MaterialMapper;
import com.sophia.cake.mapper.MiddleMapper;
import com.sophia.cake.utils.DbUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * @author lizhe
 * @date 2020-03-25 22:09
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class Import {
    @Autowired
    private DbUtils dbUtils;
    @Resource
    private BasicMapper basicMapper;
    @Resource
    private MaterialMapper materialMapper;
    @Resource
    private MiddleMapper middleMapper;
    @Resource
    private FormulaMapper formulaMapper;

    @Test
    public void importMaterials() {
        List<Material> materials = dbUtils.getEntity().getFirst();
        materials.forEach(material -> materialMapper.addMaterial(material));
    }

    @Test
    public void importBasics() {
        List<Basic> basics = dbUtils.getEntity().getSecond();
        basics.forEach(basic -> {
            basic.setCapacity(1f);
            basic.setUnit("个");
            basicMapper.addBasic(basic);
            // 获取到了basic的ID值
            Integer basicId = basic.getId();
            Set<MaterialFormula> materialFormulas = basic.getMaterialFormulaSet();
            materialFormulas.forEach(materialFormula -> {
                FormulaVo vo = new FormulaVo();
                vo.setCount(materialFormula.getCount());
                vo.setPrice(materialFormula.getPrice());
                vo.setType(FormulaType.MATERIAL.getValue());
                String name = materialFormula.getMaterial().getName();
                vo.setName(materialFormula.getMaterial().getName());
                Integer materialId = materialMapper.findMaterialByName(name).getId();
                vo.setId(materialId);
                vo.setPid(basicId);
                formulaMapper.addMaterialFormulaInBasic(vo);
            });
        });
    }
    @Test
    public void importMiddle(){
        List<Middle> middles = dbUtils.getEntity().getThird();
        middles.forEach(middle -> {
            middle.setCapacity(1f);
            middle.setUnit("个");
            middleMapper.addMiddle(middle);
            Integer middleId = middle.getId();
            Set<MaterialFormula> materialFormulas = middle.getMaterialFormulaSet();
            materialFormulas.forEach(materialFormula -> {
                FormulaVo vo = new FormulaVo();
                vo.setCount(materialFormula.getCount());
                vo.setPrice(materialFormula.getPrice());
                vo.setType(FormulaType.MATERIAL.getValue());
                String name = materialFormula.getMaterial().getName();
                vo.setName(materialFormula.getMaterial().getName());
                Integer materialId = materialMapper.findMaterialByName(name).getId();
                vo.setId(materialId);
                vo.setPid(middleId);
                formulaMapper.addMaterialFormulaInMiddle(vo);
            });

            Set<BasicFormula> basicFormulas = middle.getBasicFormulaSet();
            basicFormulas.forEach(basicFormula -> {
                FormulaVo vo = new FormulaVo();
                vo.setCount(basicFormula.getCount());
                vo.setPrice(basicFormula.getPrice());
                vo.setType(FormulaType.BASIC.getValue());
                String name = basicFormula.getBasic().getName();
                vo.setName(basicFormula.getBasic().getName());
                Integer basicId = basicMapper.findBasicByName(name).getId();
                vo.setId(basicId);
                vo.setPid(middleId);
                formulaMapper.addBasicFormulaInMiddle(vo);
            });
        });
    }

}
