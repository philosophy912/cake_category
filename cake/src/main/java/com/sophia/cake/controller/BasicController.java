package com.sophia.cake.controller;

import com.philosophy.base.entity.ResultResponse;
import com.sophia.cake.entity.vo.BasicVo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

/**
 * @author lizhe
 * @date 2020/3/26 14:41
 **/
@RestController
@RequestMapping("/basic")
public class BasicController extends BaseService{
    
    @ResponseBody
    @RequestMapping("/query")
    public ResultResponse<BasicVo> query() {
        ResultResponse<BasicVo> response = new ResultResponse<>();
        List<BasicVo> middles = basicService.query();
        if (middles.size() == 0) {
            response.setSuccess(false);
        }
        response.setData(middles);
        return response;
    }

    @ResponseBody
    @RequestMapping("/add")
    public ResultResponse<BasicVo> add(@RequestBody BasicVo basicVo) {
        ResultResponse<BasicVo> response = new ResultResponse<>();
        try {
            basicService.add(basicVo);
            response.setData(Collections.singletonList(basicVo));
        } catch (RuntimeException e) {
            response.setSuccess(false);
        }
        return response;
    }

    @ResponseBody
    @RequestMapping("/update")
    public ResultResponse<BasicVo> update(@RequestBody BasicVo basicVo) {
        ResultResponse<BasicVo> response = new ResultResponse<>();
        try {
            basicService.update(basicVo);
            response.setData(Collections.singletonList(basicVo));
        } catch (RuntimeException e) {
            response.setSuccess(false);
        }
        return response;
    }

    @ResponseBody
    @RequestMapping("/delete")
    public ResultResponse<BasicVo> delete(@RequestBody BasicVo basicVo) {
        ResultResponse<BasicVo> response = new ResultResponse<>();
        try {
            basicService.delete(basicVo);
            response.setData(Collections.singletonList(basicVo));
        } catch (RuntimeException e) {
            response.setSuccess(false);
        }
        return response;
    }
}
