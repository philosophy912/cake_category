package com.sophia.cake.controller;

import com.philosophy.base.common.Pair;
import com.philosophy.base.entity.EnvData;
import com.philosophy.base.entity.ResultResponse;
import com.sophia.cake.entity.bo.EntityBo;
import com.sophia.cake.entity.bo.NameBo;
import com.sophia.cake.entity.vo.BVo;
import com.sophia.cake.entity.vo.BasicVo;
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
 * @date 2020/3/26 14:41
 **/
@RestController
@RequestMapping("/basic")
@Api(tags = "基础产品相关接口")
@Slf4j
public class BasicController extends BaseController {

    @ResponseBody
    @RequestMapping(value = "/queryBasic", method = RequestMethod.GET)
    @ApiOperation("查询所有基础产品(仅包含id和name)的接口")
    public ResultResponse<BVo> queryBasic() {
        ResultResponse<BVo> response = new ResultResponse<>();
        List<BVo> bVos = basicService.queryBasic();
        response.setData(bVos);
        return response;
    }


    @ApiOperation("查询所有基础产品的接口")
    @ResponseBody
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @ApiImplicitParam(name = "entityBo", value = "分页信息", required = true)
    public ResultResponse<BasicVo> query(EntityBo entityBo) {
        ResultResponse<BasicVo> response = new ResultResponse<>();
        Pair<List<BasicVo>, EnvData> query = basicService.query(entityBo);
        response.setData(query.getFirst());
        response.setEnvData(query.getSecond());
        return response;
    }


    @ResponseBody
    @RequestMapping(value = "/queryName", method = RequestMethod.POST)
    @ApiOperation("根据名字查询基础产品")
    @ApiImplicitParam(name = "nameBo", value = "中级产品名字", required = true)
    public ResultResponse<BasicVo> queryByName(NameBo nameBo) {
        ResultResponse<BasicVo> response = new ResultResponse<>();
        Pair<List<BasicVo>, EnvData> query = basicService.queryName(nameBo);
        response.setData(query.getFirst());
        response.setEnvData(query.getSecond());
        return response;
    }


    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiOperation("添加基础产品")
    public ResultResponse<BasicVo> add(@RequestBody BasicVo basicVo) {
        ResultResponse<BasicVo> response = new ResultResponse<>();
        try {
            basicService.add(basicVo);
            response.setData(Collections.singletonList(basicVo));
        } catch (Exception e) {
            log.warn("found issue , the message is {}", e.getMessage());
            response.setSuccess(false);
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiOperation("更新基础产品")
    public ResultResponse<BasicVo> update(@RequestBody BasicVo basicVo) {
        ResultResponse<BasicVo> response = new ResultResponse<>();
        try {
            basicService.update(basicVo);
            response.setData(Collections.singletonList(basicVo));
        } catch (Exception e) {
            log.warn("found issue , the message is {}", e.getMessage());
            response.setSuccess(false);
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ApiOperation("删除基础产品")
    public ResultResponse<BasicVo> delete(@RequestBody BasicVo basicVo) {
        ResultResponse<BasicVo> response = new ResultResponse<>();
        try {
            basicService.delete(basicVo);
            response.setData(Collections.singletonList(basicVo));
        } catch (Exception e) {
            log.warn("found issue , the message is {}", e.getMessage());
            response.setSuccess(false);
        }
        return response;
    }
}
