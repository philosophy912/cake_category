package com.sophia.cake.controller;


import com.sophia.cake.dao.BasicProductDao;
import com.sophia.cake.dao.MaterialProductDao;
import com.sophia.cake.service.BasicService;
import com.sophia.cake.service.ImportService;
import com.sophia.cake.service.MaterialService;
import com.sophia.cake.service.MiddleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

/**
 * @author lizhe
 * @date 2020-03-04 22:36
 */
@Slf4j
public abstract class BaseController {

    @Autowired
    protected ImportService importService;
    @Autowired
    protected MaterialService materialService;
    @Autowired
    protected BasicService basicService;
    @Autowired
    protected MaterialProductDao materialProductDao;
    @Autowired
    protected BasicProductDao basicProductDao;
    @Autowired
    protected MiddleService middleService;



}
