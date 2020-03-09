package com.sophia.cake.controller;

import com.philosophy.base.entity.ResultResponse;
import com.sophia.cake.entity.Basic;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author lizhe
 * @date 2020-02-25 11:34
 */
@RestController
@RequestMapping("/basic")
public class BasicController extends BaseController {

    @RequestMapping("/query")
    public ResultResponse<Basic> queryMaterial() {
        ResultResponse<Basic> response = new ResultResponse<>();
        response.setData(basicService.query());
        return response;
    }

    @RequestMapping("/update")
    public ResultResponse<Basic> updateMaterial(@RequestBody Basic basic) {
        ResultResponse<Basic> response = new ResultResponse<>();
        response.setSuccess(basicService.update(basic));
        return response;
    }

    @RequestMapping("/add")
    public ResultResponse<Basic> addMaterial(@RequestBody Basic basic) {
        ResultResponse<Basic> response = new ResultResponse<>();
        response.setSuccess(basicService.add(basic));
        return response;
    }

    @RequestMapping("/delete")
    public ResultResponse<Basic> deleteMaterial(@RequestBody Basic basic) {
        ResultResponse<Basic> response = new ResultResponse<>();
        response.setSuccess(basicService.delete(basic));
        return response;
    }

}
