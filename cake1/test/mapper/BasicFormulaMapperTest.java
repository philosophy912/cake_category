package com.sophia.cake.mapper;

import com.sophia.cake.CakeApplication;
import com.sophia.cake.entity.po.BasicFormula;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.List;

/**
 * @author lizhe
 * @date 2020/3/17 16:12
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CakeApplication.class)
@Slf4j
class BasicFormulaMapperTest {

    @Resource
    private BasicFormulaMapper basicFormulaMapper;

    @Test
    void findById() {
        List<BasicFormula> basicFormulas = basicFormulaMapper.findById(1);
        for (BasicFormula formula : basicFormulas) {
            log.info("formula is {}", formula);
        }
    }

    @Test
    void add() {
    }

    @Test
    void updateMiddle() {
    }

    @Test
    void updateBasic() {
    }
}