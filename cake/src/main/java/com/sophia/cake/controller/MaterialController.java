package com.sophia.cake.controller;

import com.philosophy.base.entity.ResultResponse;
import com.sophia.cake.entity.po.Material;
import com.sophia.cake.entity.vo.MVo;
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
public class MaterialController extends BaseService {

    @ResponseBody
    @RequestMapping(value = "/queryMaterial",  method = RequestMethod.GET)
    public ResultResponse<MVo> queryBasic() {
        ResultResponse<MVo> response = new ResultResponse<>();
        List<MVo> mVos = materialService.queryMaterial();
        response.setData(mVos);
        return response;
    }


    @ResponseBody
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public ResultResponse<Material> query() {
        ResultResponse<Material> response = new ResultResponse<>();
        List<Material> materials = materialService.query();
        response.setData(materials);
        return response;
    }

    @ResponseBody
    @RequestMapping(value = "/queryName", method = RequestMethod.GET)
    public ResultResponse<Material> queryByName(@RequestParam("name") String name) {
        ResultResponse<Material> response = new ResultResponse<>();
        List<Material> materials = materialService.queryName(name);
        response.setData(materials);
        return response;
    }

    @ResponseBody
    @RequestMapping("/add")
    public ResultResponse<Material> add(@RequestBody Material material) {
        ResultResponse<Material> response = new ResultResponse<>();
        response.setSuccess(materialService.add(material));
        response.setData(Collections.singletonList(material));
        return response;
    }

    @ResponseBody
    @RequestMapping("/update")
    public ResultResponse<Material> update(@RequestBody Material material) {
        ResultResponse<Material> response = new ResultResponse<>();
        response.setSuccess(materialService.update(material));
        response.setData(Collections.singletonList(material));
        return response;
    }

    @ResponseBody
    @RequestMapping("/delete")
    public ResultResponse<Material> delete(@RequestBody Material material) {
        ResultResponse<Material> response = new ResultResponse<>();
        response.setSuccess(materialService.delete(material.getId()));
        response.setData(Collections.singletonList(material));
        return response;
    }

}
