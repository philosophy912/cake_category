package com.sophia.cake.controller;

import com.sophia.cake.entity.Basic;
import com.sophia.cake.entity.Middle;
import com.sophia.cake.entity.response.ResultResponse;
import com.sophia.cake.service.MiddleProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.xml.ws.Action;

/**
 * @author lizhe
 * @date 2020-02-25 11:34
 */
@Controller
@RequestMapping("/middle")
public class MiddleProductController {

    private MiddleProductService service;

    @Autowired
    public void setService(MiddleProductService service) {
        this.service = service;
    }

    @RequestMapping("/add")
    public ResultResponse<Middle> addMaterial(@RequestBody Middle middle) {
        ResultResponse<Middle> response = new ResultResponse<>();
        response.setSuccess(service.add(middle) != null);
        return response;
    }

}
