package com.sophia.cake.controller;

import com.philosophy.base.entity.ResultResponse;
import com.sophia.cake.entity.vo.MiddleVo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

/**
 * @author lizhe
 * @date 2020/3/26 14:42
 **/
@RestController
@RequestMapping("/middle")
public class MiddleController extends BaseService {

    @ResponseBody
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public ResultResponse<MiddleVo> query() {
        ResultResponse<MiddleVo> response = new ResultResponse<>();
        List<MiddleVo> middleVos = middleService.query();
        response.setData(middleVos);
        return response;
    }

    @ResponseBody
    @RequestMapping(value = "/queryName", method = RequestMethod.GET)
    public ResultResponse<MiddleVo> queryByName(@RequestParam("name") String name) {
        ResultResponse<MiddleVo> response = new ResultResponse<>();
        List<MiddleVo> middleVos = middleService.queryName(name);
        response.setData(middleVos);
        return response;
    }

    @ResponseBody
    @RequestMapping("/add")
    public ResultResponse<MiddleVo> add(@RequestBody MiddleVo middleVo) {
        ResultResponse<MiddleVo> response = new ResultResponse<>();
        try {
            middleService.add(middleVo);
            response.setData(Collections.singletonList(middleVo));
        } catch (RuntimeException e) {
            response.setSuccess(false);
        }
        return response;
    }

    @ResponseBody
    @RequestMapping("/update")
    public ResultResponse<MiddleVo> update(@RequestBody MiddleVo middleVo) {
        ResultResponse<MiddleVo> response = new ResultResponse<>();
        try {
            middleService.update(middleVo);
            response.setData(Collections.singletonList(middleVo));
        } catch (RuntimeException e) {
            response.setSuccess(false);
        }
        return response;
    }

    @ResponseBody
    @RequestMapping("/delete")
    public ResultResponse<MiddleVo> delete(@RequestBody MiddleVo middleVo) {
        ResultResponse<MiddleVo> response = new ResultResponse<>();
        try {
            middleService.delete(middleVo);
            response.setData(Collections.singletonList(middleVo));
        } catch (RuntimeException e) {
            response.setSuccess(false);
        }
        return response;
    }

}
