package com.sophia.cake.controller;

import com.philosophy.base.common.Triple;
import com.sophia.cake.entity.Basic;
import com.sophia.cake.entity.Material;
import com.sophia.cake.entity.Middle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author lizhe
 * @Description description
 * @date 2020/3/6 13:40
 **/
@RestController
@Slf4j
public class DataController extends BaseController{



    private Triple<List<Material>, List<Basic>, List<Middle>> getEntity() {
        return excelService.getEntity();
    }


    @RequestMapping("/materials")
    public String updateMaterials() {
        List<Material> materials = getEntity().getFirst();
        materials.forEach(material -> materialService.add(material));
        return "success";
    }

    @RequestMapping("/basics")
    public String updateBasics() {
        List<Basic> basics = getEntity().getSecond();
        basics.forEach(basic -> basicService.add(basic));
        return "success";
    }

    @RequestMapping("/middles")
    public String updateMiddles() {
        List<Middle> middles = getEntity().getThird();
        middles.forEach(middle -> middleService.add(middle));
        return "success";
    }


    @RequestMapping("/")
    public String update() {
        Triple<List<Material>, List<Basic>, List<Middle>> triple = getEntity();
        List<Material> materials = triple.getFirst();
        List<Basic> basics = triple.getSecond();
        List<Middle> middles = triple.getThird();
        log.warn("materials = {}, basics = {}, middles = {}", materials.size(), basics.size(), middles.size());
        middles.forEach(middle -> log.warn("middle = {}", middle));
        materials.forEach(material -> materialService.add(material));
        basics.forEach(basic -> basicService.add(basic));
        middles.forEach(middle -> middleService.add(middle));
        return "hello world";
    }
}
