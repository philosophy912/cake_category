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
    public ResultResponse<MaterialProduct> queryBasic() {
        ResultResponse<MaterialProduct> response = new ResultResponse<>();
        response.setData(service.queryMaterials());
        return response;
    }

    @RequestMapping("/update")
    public ResultResponse<MaterialProduct> updateBasic(@RequestBody MaterialProduct materialProduct) {
        ResultResponse<MaterialProduct> response = new ResultResponse<>();
        Boolean updateResult = service.updateMaterial(materialProduct);
        response.setSuccess(updateResult);
        return response;
    }

}
