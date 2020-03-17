package com.sophia.cake.controller;

import com.sophia.cake.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author lizhe
 * @date 2020/3/17 16:20
 **/

public abstract class Controller {
    @Autowired
    protected MaterialService materialService;
}
