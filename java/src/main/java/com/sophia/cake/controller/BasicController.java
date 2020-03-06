package com.sophia.cake.controller;

import com.sophia.cake.entity.Basic;
import com.sophia.cake.entity.response.ResultResponse;
import com.sophia.cake.service.entity.BasicService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author lizhe
 * @date 2020-02-25 11:33
 */
@Controller
@RequestMapping("/basic")
public class BasicController {


    private BasicService basicService;

    public void setService(BasicService service) {
        this.basicService = service;
    }

    @RequestMapping("/add")
    public ResultResponse<Basic> addMaterial(@RequestBody Basic basic) {
        ResultResponse<Basic> response = new ResultResponse<>();
        response.setSuccess(basicService.add(basic) != null);
        return response;
    }
}
