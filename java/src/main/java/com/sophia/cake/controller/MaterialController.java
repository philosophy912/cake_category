package com.sophia.cake.controller;

import com.sophia.cake.entity.Material;
import com.sophia.cake.entity.response.ResultResponse;
import com.sophia.cake.service.entity.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author lizhe
 * @date 2020-02-25 11:34
 */
@RestController
@RequestMapping("/material")
public class MaterialController {

    private MaterialService materialService;

    @Autowired
    public void setService(MaterialService materialService) {
        this.materialService = materialService;
    }

    @RequestMapping("/query")
    public ResultResponse<Material> queryMaterial() {
        ResultResponse<Material> response = new ResultResponse<>();
        response.setData(materialService.query());
        return response;
    }

    @RequestMapping("/update")
    public ResultResponse<Material> updateMaterial(@RequestBody Material material) {
        ResultResponse<Material> response = new ResultResponse<>();
        response.setSuccess(materialService.update(material) != null);
        return response;
    }

    @RequestMapping("/add")
    public ResultResponse<Material> addMaterial(@RequestBody Material materialProduct) {
        ResultResponse<Material> response = new ResultResponse<>();
        response.setSuccess(materialService.add(materialProduct) != null);
        return response;
    }

    @RequestMapping("/delete")
    public ResultResponse<Material> deleteMaterial(@RequestBody Material material) {
        ResultResponse<Material> response = new ResultResponse<>();
        response.setSuccess(materialService.delete(material));
        return response;
    }

}