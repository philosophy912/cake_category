package com.sophia.cake.controller;

import com.philosophy.base.entity.ResultResponse;
import com.sophia.cake.entity.vo.MiddleVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "中级产品相关接口")
public class MiddleController extends BaseService {

    @ApiOperation("查询所有中级产品的接口")
    @ResponseBody
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public ResultResponse<MiddleVo> query() {
        ResultResponse<MiddleVo> response = new ResultResponse<>();
        List<MiddleVo> middleVos = middleService.query();
        response.setData(middleVos);
        return response;
    }

    @ApiOperation("根据名字查询中级产品")
    @ApiImplicitParam(name = "name", value = "中级产品名字", required = true)
    @ResponseBody
    @RequestMapping(value = "/queryName", method = RequestMethod.GET)
    public ResultResponse<MiddleVo> queryByName(@RequestParam("name") String name) {
        ResultResponse<MiddleVo> response = new ResultResponse<>();
        List<MiddleVo> middleVos = middleService.queryName(name);
        response.setData(middleVos);
        return response;
    }

    @ApiOperation("添加中级产品")
    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
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

    @ApiOperation("更新中级产品")
    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
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

    @ApiOperation("删除中级产品")
    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
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
