package com.sophia.cake.controller;


import com.sophia.cake.service.ImportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author lizhe
 * @date 2020-03-04 22:36
 */
@Slf4j
public abstract class BaseController {

    @Autowired
    protected ImportService importService;


}
