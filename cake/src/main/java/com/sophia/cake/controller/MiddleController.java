package com.sophia.cake.controller;

import com.philosophy.base.common.Pair;
import com.philosophy.base.entity.EnvData;
import com.philosophy.base.entity.ResultResponse;
import com.sophia.cake.entity.bo.EntityBo;
import com.sophia.cake.entity.bo.NameBo;
import com.sophia.cake.entity.vo.MiddleVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
@Slf4j
public class MiddleController extends BaseController {

    @ApiOperation("查询所有中级产品的接口")
    @ResponseBody
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @ApiImplicitParam(name = "entityBo", value = "分页信息", required = true)
    public ResultResponse<MiddleVo> query(EntityBo entityBo) {
        ResultResponse<MiddleVo> response = new ResultResponse<>();
        Pair<List<MiddleVo>, EnvData> query = middleService.query(entityBo);
        response.setData(query.getFirst());
        response.setEnvData(query.getSecond());
        return response;
    }

    @ApiOperation("根据名字查询中级产品")
    @ApiImplicitParam(name = "nameBo", value = "中级产品名字", required = true)
    @ResponseBody
    @RequestMapping(value = "/queryName", method = RequestMethod.POST)
    public ResultResponse<MiddleVo> queryByName(NameBo nameBo) {
        ResultResponse<MiddleVo> response = new ResultResponse<>();
        Pair<List<MiddleVo>, EnvData> query = middleService.queryName(nameBo);
        response.setData(query.getFirst());
        response.setEnvData(query.getSecond());
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
            log.warn("found issue , the message is {}", e.getMessage());
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
            log.warn("found issue , the message is {}", e.getMessage());
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
            log.warn("found issue , the message is {}", e.getMessage());
            response.setSuccess(false);
        }
        return response;
    }

}
