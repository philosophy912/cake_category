package com.sophia.cake.dao;

import com.sophia.cake.entity.po.Basic;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
class IBasicDaoTest {

    @Resource
    private IBasicDao dao;

    @Test
    public void test1() {
        List<Basic> basics = dao.findAll();
        for (Basic basic : basics) {
            System.out.println(basic);
        }

    }

}