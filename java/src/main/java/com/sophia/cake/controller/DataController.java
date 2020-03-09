package com.sophia.cake.controller;

import com.philosophy.base.common.Triple;
import com.philosophy.base.entity.ResultResponse;
import com.sophia.cake.entity.Basic;
import com.sophia.cake.entity.Material;
import com.sophia.cake.entity.Middle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lizhe
 * @date 2020/3/6 13:40
 **/
@RestController
@Slf4j
public class DataController extends BaseController {


    private Triple<List<Material>, List<Basic>, List<Middle>> getEntity() {
        return importService.getEntity();
    }

    @RequestMapping("/add")
    public String addMaterial() {
        Triple<List<Material>, List<Basic>, List<Middle>> triple = getEntity();
        List<Material> materials = triple.getFirst();
        List<Basic> basics = triple.getSecond();
        List<Middle> middles = triple.getThird();
        /*materials.forEach(System.out::println);
        basics.forEach(System.out::println);
        middles.forEach(System.out::println);*/
        materials.forEach(material -> {
            log.info("material name = {}", material.getName());
            materialService.add(material);
        });
        basics.forEach(basic -> basic.getMaterialProducts().forEach(materialProduct -> {
            try {
                materialProductDao.add(materialProduct);
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        }));
        basics.forEach(basic -> {
            basicService.add(basic);
            log.info("basic name = {}", basic.getName());
        });
        middles.forEach(middle -> {
            middle.getMaterialProducts().forEach(materialProduct -> {
                try {
                    materialProductDao.add(materialProduct);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            middle.getBasicProducts().forEach(basicProduct -> {
                try {
                    basicProductDao.add(basicProduct);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        });
        middles.forEach(middle -> {
            log.info("middle name = {}", middle.getName());
            middleService.add(middle);
        });
        return "success";
    }


    @RequestMapping("/")
    public ResultResponse<Triple<List<Material>, List<Basic>, List<Middle>>> update() {
        Triple<List<Material>, List<Basic>, List<Middle>> triple = getEntity();
        List<Triple<List<Material>, List<Basic>, List<Middle>>> data = new ArrayList<>();
        data.add(triple);
        ResultResponse<Triple<List<Material>, List<Basic>, List<Middle>>> response = new ResultResponse<>();
        response.setData(data);
        response.setSuccess(true);
        return response;
    }
}
