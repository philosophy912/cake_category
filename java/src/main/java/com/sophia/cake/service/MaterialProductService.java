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
            // 重新赋值，涉及到计算的地方重新计算
            updateUtil.copyNullProperties(product, materialProduct);
            log.debug("product is " + product);
            log.debug("materialProduct is " + materialProduct);
            materialProductDao.saveAndFlush(product);
            return true;
        }else{
            return false;
        }
    }

    public MaterialProduct addMaterial(MaterialProduct materialProduct){
        MaterialProduct product = null;
        log.info("material = {}",materialProduct);
        if(null!=materialProduct.getPrice() && null!=materialProduct.getCapacity()){
            // 先计算出来每单位容量的价格然后再添加到数据库中
            materialProduct.setPricePerCapacity(materialProduct.getPrice()/materialProduct.getCapacity());
            product =  materialProductDao.saveAndFlush(materialProduct);
        }
        log.info("product = {}", product);
        return product;
    }

    public Boolean deleteMaterial(MaterialProduct materialProduct){
        try {
            materialProductDao.delete(materialProduct);
            return true;
        }catch (IllegalArgumentException e){
            return false;
        }
    }

}
