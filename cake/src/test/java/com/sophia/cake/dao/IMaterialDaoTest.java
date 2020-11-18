package com.sophia.cake.dao;

import com.sophia.cake.entity.po.Material;
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
class IMaterialDaoTest {
    @Resource
    private IMaterialDao dao;

    @Test
    public void test1(){
        List<Material> materials = dao.findAll();
        for(Material material: materials){
            System.out.println(material);
        }

    }

}