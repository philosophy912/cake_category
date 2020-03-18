package com.sophia.cake.controller;

import com.philosophy.base.entity.ResultResponse;
import com.sophia.cake.entity.Material;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author lizhe
 * @date 2020/3/17 16:21
 **/
@RestController
@RequestMapping("/material")
public class MaterialController extends BaseController {

    @ResponseBody
    @RequestMapping("/query")
    public ResultResponse<Material> query() {
        ResultResponse<Material> response = new ResultResponse<>();
        List<Material> materialList = materialService.findAll();
        if (materialList.size() == 0) {
            response.setSuccess(false);
        }
        response.setData(materialList);
        return response;
    }

    @ResponseBody
    @RequestMapping("/add")
    public ResultResponse<Material> add(@RequestBody Material material) {
        ResultResponse<Material> response = new ResultResponse<>();
        utils.update(material);
        response.setSuccess(materialService.add(material));
        return response;
    }

    @ResponseBody
    @RequestMapping("/update")
    public ResultResponse<Material> update(@RequestBody Material material) {
        ResultResponse<Material> response = new ResultResponse<>();
        response.setSuccess(materialService.update(material));
        return response;
    }

    @ResponseBody
    @RequestMapping("/delete")
    public ResultResponse<Material> delete(@RequestBody Material material){
        ResultResponse<Material> response = new ResultResponse<>();
        response.setSuccess(materialService.delete(material.getId()));
        return response;
    }


}
