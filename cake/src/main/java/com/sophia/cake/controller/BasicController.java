package com.sophia.cake.controller;

import com.philosophy.base.entity.ResultResponse;
import com.sophia.cake.entity.po.Material;
import com.sophia.cake.entity.vo.BVo;
import com.sophia.cake.entity.vo.BasicVo;
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
 * @date 2020/3/26 14:41
 **/
@RestController
@RequestMapping("/basic")
public class BasicController extends BaseService{

    @ResponseBody
    @RequestMapping(value = "/queryBasic",  method = RequestMethod.GET)
    public ResultResponse<BVo> queryBasic(){
        ResultResponse<BVo> response = new ResultResponse<>();
        List<BVo> bVos = basicService.queryBasic();
        response.setData(bVos);
        return response;
    }



    @ResponseBody
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public ResultResponse<BasicVo> query() {
        ResultResponse<BasicVo> response = new ResultResponse<>();
        List<BasicVo> basicVos = basicService.query();
        response.setData(basicVos);
        return response;
    }

    @ResponseBody
    @RequestMapping(value = "/queryName", method = RequestMethod.GET)
    public ResultResponse<BasicVo> queryByName(@RequestParam("name") String name) {
        ResultResponse<BasicVo> response = new ResultResponse<>();
        List<BasicVo> basicVos = basicService.queryName(name);
        response.setData(basicVos);
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
