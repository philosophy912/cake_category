package com.sophia.cake.controller;

import com.sophia.cake.entity.Basic;
import com.sophia.cake.entity.Material;
import com.sophia.cake.entity.response.ResultResponse;
import com.sophia.cake.service.BasicProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author lizhe
 * @date 2020-02-25 11:33
 */
@Controller
@RequestMapping("/basic")
public class BasicProductController {


    private BasicProductService service;

    public void setService(BasicProductService service) {
        this.service = service;
    }

    @RequestMapping("/add")
    public ResultResponse<Basic> addMaterial(@RequestBody Basic basic) {
        ResultResponse<Basic> response = new ResultResponse<>();
        response.setSuccess(service.add(basic) != null);
        return response;
    }
}
