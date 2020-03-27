package com.sophia.cake.controller;

import com.sophia.cake.service.BasicService;
import com.sophia.cake.service.MaterialService;
import com.sophia.cake.service.MiddleService;
import com.sophia.cake.utils.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author lizhe
 * @date 2020/3/26 14:25
 **/
public abstract class BaseService {
    @Autowired
    protected EntityUtils utils;
    @Autowired
    protected MaterialService materialService;
    @Autowired
    protected BasicService basicService;
    @Autowired
    protected MiddleService middleService;
}