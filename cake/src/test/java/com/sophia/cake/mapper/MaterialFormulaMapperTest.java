package com.sophia.cake.mapper;

import com.sophia.cake.CakeApplication;
import com.sophia.cake.entity.MaterialFormula;
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
 * @date 2020/3/17 13:28
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CakeApplication.class)
@Slf4j
class MaterialFormulaMapperTest {
    @Resource
    private MaterialFormulaMapper materialFormulaMapper;

    @Test
    void findById() {
        List<MaterialFormula> materialFormulas = materialFormulaMapper.findById(1);
        log.info("MaterialFormula size is {}", materialFormulas.size());
        for(MaterialFormula formula: materialFormulas){
            log.info("formula is {}", formula);
        }

    }

    @Test
    void add() {
    }

    @Test
    void updateMaterial() {
    }

    @Test
    void updateMiddle() {
    }

    @Test
    void updateBasic() {
    }
}