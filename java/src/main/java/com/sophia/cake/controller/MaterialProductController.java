package com.sophia.cake.controller;

import com.sophia.cake.entity.Material;
import com.sophia.cake.entity.response.ResultResponse;
import com.sophia.cake.service.MaterialProductService;
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
public class MaterialProductController {

    private MaterialProductService service;

    @Autowired
    public void setService(MaterialProductService service) {
        this.service = service;
    }

    @RequestMapping("/query")
    public ResultResponse<Material> queryMaterial() {
        ResultResponse<Material> response = new ResultResponse<>();
        response.setData(service.queryMaterials());
        return response;
    }

    @RequestMapping("/update")
    public ResultResponse<Material> updateMaterial(@RequestBody Material materialProduct) {
        ResultResponse<Material> response = new ResultResponse<>();
        response.setSuccess(service.updateMaterial(materialProduct));
        return response;
    }

    @RequestMapping("/add")
    public ResultResponse<Material> addMaterial(@RequestBody Material materialProduct) {
        ResultResponse<Material> response = new ResultResponse<>();
        response.setSuccess(service.addMaterial(materialProduct) != null);
        return response;
    }

    @RequestMapping("/delete")
    public ResultResponse<Material> deleteMaterial(@RequestBody Material materialProduct){
        ResultResponse<Material> response = new ResultResponse<>();
        response.setSuccess(service.deleteMaterial(materialProduct));
        return response;
    }

}
