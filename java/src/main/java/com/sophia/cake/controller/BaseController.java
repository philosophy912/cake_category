package com.sophia.cake.controller;


import com.sophia.cake.entity.Material;
import com.sophia.cake.service.entity.BasicProductService;
import com.sophia.cake.service.entity.BasicService;
import com.sophia.cake.service.base.ExcelService;
import com.sophia.cake.service.entity.MaterialProductService;
import com.sophia.cake.service.entity.MaterialService;
import com.sophia.cake.service.entity.MiddleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author lizhe
 * @date 2020-03-04 22:36
 */
@Slf4j
public abstract class BaseController {
    @Autowired
    protected MaterialService materialService;
    @Autowired
    protected MaterialProductService materialProductService;
    @Autowired
    protected BasicService basicService;
    @Autowired
    protected BasicProductService basicProductService;
    @Autowired
    protected MiddleService middleService;
    @Autowired
    protected ExcelService excelService;


}
