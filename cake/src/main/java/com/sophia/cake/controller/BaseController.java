package com.sophia.cake.controller;

import com.sophia.cake.service.api.IBasicService;
import com.sophia.cake.service.api.IMaterialService;
import com.sophia.cake.service.api.IMiddleService;
import com.sophia.cake.utils.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author lizhe
 * @date 2020/3/26 14:25
 **/
public abstract class BaseController {
    @Autowired
    protected EntityUtils utils;
    @Autowired
    protected IMaterialService materialService;
    @Autowired
    protected IBasicService basicService;
    @Autowired
    protected IMiddleService middleService;
}
