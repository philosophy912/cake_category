package com.sophia.cake.controller;

import com.sophia.cake.service.impl.BasicService;
import com.sophia.cake.service.impl.MaterialService;
import com.sophia.cake.service.impl.MiddleService;
import com.sophia.cake.utils.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author lizhe
 * @date 2020/3/17 16:20
 **/

public abstract class BaseController {
    @Autowired
    protected EntityUtils utils;
    @Autowired
    protected MaterialService materialService;
    @Autowired
    protected BasicService basicService;
    @Autowired
    protected MiddleService middleService;

}
