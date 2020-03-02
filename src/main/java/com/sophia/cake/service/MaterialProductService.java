package com.sophia.cake.service;

import com.sophia.cake.dao.MaterialProductDao;
import com.sophia.cake.entity.MaterialProduct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author lizhe
 * @date 2020-02-25 11:31
 */
@Service
@Slf4j
public class MaterialProductService extends BaseService {

    private MaterialProductDao materialProductDao;
    @Autowired
    public void setMaterialProductDao(MaterialProductDao materialProductDao) {
        this.materialProductDao = materialProductDao;
    }


    public List<MaterialProduct> queryMaterials(){
        return materialProductDao.findAll();
    }

    public Boolean updateMaterial(MaterialProduct materialProduct){
        MaterialProduct searchMaterial = new MaterialProduct();
        searchMaterial.setId(materialProduct.getId());
        Example<MaterialProduct> example = Example.of(searchMaterial);
        Optional<MaterialProduct> materialProductOptional = materialProductDao.findOne(example);
        // 能够在数据库中查找到数据
        if(materialProductOptional.isPresent()){
            MaterialProduct product = materialProductOptional.get();
            updateUtil.copyNullProperties(product, materialProduct);
            log.debug("product is " + product);
            log.debug("materialProduct is " + materialProduct);
        }else{
            return false;
        }
        return true;
    }

}
