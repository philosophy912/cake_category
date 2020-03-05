package com.sophia.cake.controller;

import com.philosophy.base.common.Triple;
import com.sophia.cake.entity.Basic;
import com.sophia.cake.entity.Material;
import com.sophia.cake.entity.Middle;
import com.sophia.cake.service.BasicProductService;
import com.sophia.cake.service.ExcelService;
import com.sophia.cake.service.MaterialProductService;
import com.sophia.cake.service.MiddleProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author lizhe
 * @date 2020-03-04 22:36
 */
@RestController
@Slf4j
public class BaseController {

    private MaterialProductService materialProductService;
    private BasicProductService basicProductService;
    private MiddleProductService middleProductService;
    private ExcelService excelService;

    @Autowired
    public void setMaterialProductService(MaterialProductService materialProductService) {
        this.materialProductService = materialProductService;
    }

    @Autowired
    public void setBasicProductService(BasicProductService basicProductService) {
        this.basicProductService = basicProductService;
    }

    @Autowired
    public void setMiddleProductService(MiddleProductService middleProductService) {
        this.middleProductService = middleProductService;
    }

    @Autowired
    public void setExcelService(ExcelService excelService) {
        this.excelService = excelService;
    }

    @RequestMapping("/")
    public String update() {
        Triple<List<Material>, List<Basic>, List<Middle>> triple = excelService.getEntity();
        List<Material> materials = triple.getFirst();
        List<Basic> basics = triple.getSecond();
        List<Middle> middles = triple.getThird();
        log.warn("materials = {}, basics = {}, middles = {}", materials.size(), basics.size(), middles.size());
        middles.forEach(middle -> log.warn("middle = {}", middle));
        materials.forEach(material -> materialProductService.add(material));
        basics.forEach(basic -> basicProductService.add(basic));
        middles.forEach(middle -> middleProductService.add(middle));
        return "hello world";
    }
}
