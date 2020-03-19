package com.sophia.cake.controller;

import com.philosophy.base.entity.ResultResponse;
import com.sophia.cake.entity.Middle;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author lizhe
 * @date 2020-03-18 22:17
 */
@RestController
@RequestMapping("/middle")
public class MiddleController extends BaseController {

    @ResponseBody
    @RequestMapping("/query")
    public ResultResponse<Middle> query() {
        ResultResponse<Middle> response = new ResultResponse<>();
        List<Middle> middles = middleService.findAll();
        if (middles.size() == 0) {
            response.setSuccess(false);
        }
        response.setData(middles);
        return response;
    }

    @ResponseBody
    @RequestMapping("/add")
    public ResultResponse<Middle> add(@RequestBody Middle middle) {
        ResultResponse<Middle> response = new ResultResponse<>();
        utils.update(middle);
        response.setSuccess(middleService.add(middle));
        return response;
    }


    @ResponseBody
    @RequestMapping("/update")
    public ResultResponse<Middle> update(@RequestBody Middle middle) {
        ResultResponse<Middle> response = new ResultResponse<>();
        response.setSuccess(middleService.update(middle));
        return response;
    }

    @ResponseBody
    @RequestMapping("/delete")
    public ResultResponse<Middle> delete(@RequestBody Middle middle) {
        ResultResponse<Middle> response = new ResultResponse<>();
        response.setSuccess(middleService.delete(middle.getId()));
        return response;
    }
}
