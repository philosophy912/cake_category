package com.sophia.cake.controller;

import com.philosophy.base.entity.ResultResponse;
import com.sophia.cake.entity.Middle;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author lizhe
 * @date 2020-02-25 11:34
 */
@RestController
@RequestMapping("/middle")
public class MiddleController extends BaseController {

    @RequestMapping("/query")
    public ResultResponse<Middle> queryMaterial() {
        ResultResponse<Middle> response = new ResultResponse<>();
        response.setData(middleService.query());
        return response;
    }

    @RequestMapping("/update")
    public ResultResponse<Middle> updateMaterial(@RequestBody Middle middle) {
        ResultResponse<Middle> response = new ResultResponse<>();
        response.setSuccess(middleService.update(middle));
        return response;
    }

    @RequestMapping("/add")
    public ResultResponse<Middle> addMaterial(@RequestBody Middle middle) {
        ResultResponse<Middle> response = new ResultResponse<>();
        response.setSuccess(middleService.add(middle));
        return response;
    }

    @RequestMapping("/delete")
    public ResultResponse<Middle> deleteMaterial(@RequestBody Middle middle) {
        ResultResponse<Middle> response = new ResultResponse<>();
        response.setSuccess(middleService.delete(middle));
        return response;
    }

}
