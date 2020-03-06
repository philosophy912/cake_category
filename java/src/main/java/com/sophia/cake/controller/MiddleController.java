package com.sophia.cake.controller;

import com.sophia.cake.entity.Middle;
import com.sophia.cake.entity.response.ResultResponse;
import com.sophia.cake.service.entity.MiddleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author lizhe
 * @date 2020-02-25 11:34
 */
@Controller
@RequestMapping("/middle")
public class MiddleController {

    private MiddleService service;

    @Autowired
    public void setService(MiddleService service) {
        this.service = service;
    }

    @RequestMapping("/add")
    public ResultResponse<Middle> addMaterial(@RequestBody Middle middle) {
        ResultResponse<Middle> response = new ResultResponse<>();
        response.setSuccess(service.add(middle) != null);
        return response;
    }

}
