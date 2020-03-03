package com.sophia.cake.controller;

import com.sophia.cake.entity.MaterialProduct;
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
    public ResultResponse<MaterialProduct> queryMaterial() {
        ResultResponse<MaterialProduct> response = new ResultResponse<>();
        response.setData(service.queryMaterials());
        return response;
    }

    @RequestMapping("/update")
    public ResultResponse<MaterialProduct> updateMaterial(@RequestBody MaterialProduct materialProduct) {
        ResultResponse<MaterialProduct> response = new ResultResponse<>();
        response.setSuccess(service.updateMaterial(materialProduct));
        return response;
    }

    @RequestMapping("/add")
    public ResultResponse<MaterialProduct> addMaterial(@RequestBody MaterialProduct materialProduct) {
        ResultResponse<MaterialProduct> response = new ResultResponse<>();
        response.setSuccess(service.addMaterial(materialProduct) != null);
        return response;
    }

    @RequestMapping("/delete")
    public ResultResponse<MaterialProduct> deleteMaterial(@RequestBody MaterialProduct materialProduct){
        ResultResponse<MaterialProduct> response = new ResultResponse<>();
        response.setSuccess(service.deleteMaterial(materialProduct));
        return response;
    }

}
