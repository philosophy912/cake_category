package com.sophia.cake.service.impl;

import com.philosophy.base.common.Pair;
import com.philosophy.base.entity.EnvData;
import com.sophia.cake.entity.FormulaType;
import com.sophia.cake.entity.bo.EntityBo;
import com.sophia.cake.entity.bo.NameBo;
import com.sophia.cake.entity.vo.BVo;
import com.sophia.cake.entity.vo.BasicVo;
import com.sophia.cake.entity.vo.FormulaVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author lizhe
 * @date 2020/4/7 16:02
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
class BasicServiceTest {

    @Resource
    private BasicService service;

    private String type = FormulaType.MATERIAL.getValue();

    @Test
    void queryBasic() {
        List<BVo> bVos = service.queryBasic();
        assertTrue(bVos.size() > 5);
    }

    @Test
    void query() {
        EntityBo entityBo = new EntityBo();
        Pair<List<BasicVo>, EnvData> query = service.query(entityBo);
        assertTrue(query.getFirst().size() > 5);
    }

    @Test
    void queryName() {
        NameBo nameBo = new NameBo();
        nameBo.setName("蛋糕");
        Pair<List<BasicVo>, EnvData> query = service.queryName(nameBo);
        assertTrue(query.getFirst().size() > 2);
    }

    @Test
    void add() {
        BasicVo basicVo = new BasicVo();
        basicVo.setName("测试");
        basicVo.setCapacity(1f);
        basicVo.setUnit("头");

        FormulaVo formulaVo1 = new FormulaVo();
        formulaVo1.setCount(12f);
        formulaVo1.setType(type);
        formulaVo1.setId(1);

        FormulaVo formulaVo2 = new FormulaVo();
        formulaVo2.setCount(100f);
        formulaVo2.setType(type);
        formulaVo2.setId(2);

        Set<FormulaVo> formulaVos = new HashSet<>();
        formulaVos.add(formulaVo1);
        formulaVos.add(formulaVo2);

        basicVo.setFormulas(formulaVos);

        assertDoesNotThrow(() -> service.add(basicVo));
    }

    @Test
    void delete() {
        BasicVo basicVo = new BasicVo();
        basicVo.setId(120);
        assertThrows(RuntimeException.class, () -> service.delete(basicVo));
    }

    @Test
    void update() {
        EntityBo entityBo = new EntityBo();
        BasicVo basicVo = service.query(entityBo).getFirst().get(0);
        basicVo.getFormulas().forEach(formulaVo -> formulaVo.setCount(formulaVo.getCount() + 2));
        basicVo.setName(basicVo.getName() + "修改");
        assertDoesNotThrow(() -> service.update(basicVo));
    }
}