package com.sophia.cake.controller;

import com.philosophy.base.entity.ResultResponse;
import com.sophia.cake.entity.po.Material;
import com.sophia.cake.entity.vo.MVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
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
 * @date 2020/3/26 14:19
 **/
@RestController
@RequestMapping("/material")
@Api(tags = "原材料相关接口")
@Slf4j
public class MaterialController extends BaseService {

    @ResponseBody
    @RequestMapping(value = "/queryMaterial", method = RequestMethod.GET)
    @ApiOperation("查询所有原材料(仅包含id和name)的接口")
    public ResultResponse<MVo> queryBasic() {
        ResultResponse<MVo> response = new ResultResponse<>();
        List<MVo> mVos = materialService.queryMaterial();
        response.setData(mVos);
        return response;
    }


    @ResponseBody
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    @ApiOperation("查询所有原材料的接口")
    public ResultResponse<Material> query() {
        ResultResponse<Material> response = new ResultResponse<>();
        List<Material> materials = materialService.query();
        response.setData(materials);
        return response;
    }

    @ResponseBody
    @RequestMapping(value = "/queryName", method = RequestMethod.GET)
    @ApiOperation("根据名字查询基础产品")
    public ResultResponse<Material> queryByName(@RequestParam("name") String name) {
        ResultResponse<Material> response = new ResultResponse<>();
        List<Material> materials = materialService.queryName(name);
        response.setData(materials);
        return response;
    }

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiOperation("添加原材料")
    public ResultResponse<Material> add(@RequestBody Material material) {
        ResultResponse<Material> response = new ResultResponse<>();
        try {
            response.setSuccess(materialService.add(material));
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
            response.setSuccess(materialService.update(material));
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
            response.setSuccess(materialService.delete(material.getId()));
        } catch (Exception e) {
            log.warn("found issue , the message is {}", e.getMessage());
            response.setSuccess(false);
        }
        response.setData(Collections.singletonList(material));
        return response;
    }

}
