package com.sophia.cake.controller;

import com.philosophy.base.common.Pair;
import com.philosophy.base.entity.EnvData;
import com.philosophy.base.entity.ResultResponse;
import com.sophia.cake.entity.bo.EntityBo;
import com.sophia.cake.entity.bo.NameBo;
import com.sophia.cake.entity.po.Material;
import com.sophia.cake.entity.vo.MVo;
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
 * @date 2020/3/26 14:19
 **/
@RestController
@RequestMapping("/material")
@Api(tags = "原材料相关接口")
@Slf4j
public class MaterialController extends BaseController {

    @ResponseBody
    @RequestMapping(value = "/queryMaterial", method = RequestMethod.POST)
    @ApiOperation("查询所有原材料(仅包含id和name)的接口")
    public ResultResponse<MVo> queryBasic() {
        ResultResponse<MVo> response = new ResultResponse<>();
        List<MVo> mVos = materialService.queryMaterial();
        response.setData(mVos);
        return response;
    }


    @ResponseBody
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @ApiOperation("查询所有原材料的接口")
    @ApiImplicitParam(name = "entityBo", value = "分页信息", required = true)
    public ResultResponse<Material> query(@RequestBody  EntityBo entityBo) {
        ResultResponse<Material> response = new ResultResponse<>();
        Pair<List<Material>, EnvData> query = materialService.query(entityBo);
        response.setData(query.getFirst());
        response.setEnvData(query.getSecond());
        return response;
    }


    @ResponseBody
    @RequestMapping(value = "/queryName", method = RequestMethod.POST)
    @ApiOperation("根据名字查询基础产品")
    @ApiImplicitParam(name = "nameBo", value = "原材料名字", required = true)
    public ResultResponse<Material> queryByName(@RequestBody NameBo nameBo) {
        ResultResponse<Material> response = new ResultResponse<>();
        Pair<List<Material>, EnvData> query = materialService.queryName(nameBo);
        response.setData(query.getFirst());
        response.setEnvData(query.getSecond());
        return response;
    }


    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiOperation("添加原材料")
    public ResultResponse<Material> add(@RequestBody Material material) {
        ResultResponse<Material> response = new ResultResponse<>();
        try {
            materialService.add(material);
        } catch (Exception e) {
            log.warn("found issue , the message is {}", e.getMessage());
            response.setSuccess(false);
        }
        response.setData(Collections.singletonList(material));
        return response;
    }

    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiOperation("更新原材料")
    public ResultResponse<Material> update(@RequestBody Material material) {
        ResultResponse<Material> response = new ResultResponse<>();
        try {
            materialService.update(material);
        } catch (Exception e) {
            log.warn("found issue , the message is {}", e.getMessage());
            response.setSuccess(false);
        }
        response.setData(Collections.singletonList(material));
        return response;
    }

    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ApiOperation("删除原材料")
    public ResultResponse<Material> delete(@RequestBody Material material) {
        ResultResponse<Material> response = new ResultResponse<>();
        try {
            materialService.delete(material);
        } catch (Exception e) {
            log.warn("found issue , the message is {}", e.getMessage());
            response.setSuccess(false);
        }
        response.setData(Collections.singletonList(material));
        return response;
    }

}
