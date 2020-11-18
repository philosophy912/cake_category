package com.sophia.cake.dao;

import com.sophia.cake.entity.po.Middle;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
class IMiddleDaoTest {
    @Resource
    private IMiddleDao dao;

    @Test
    public void test1() {
        List<Middle> middles = dao.findAll();
        for (Middle middle : middles) {
            System.out.println(middle);
        }
    }

    @Test
    public void test2() {
        Optional<Middle> middle = dao.findById(2);
        middle.ifPresent(System.out::println);

    }

    @Test
    public void test3() {
        List<Middle> middles = dao.findByNameLike("%奶%");
        System.out.println(middles);
    }

}