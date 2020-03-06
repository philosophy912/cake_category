package com.sophia.cake.service.entity;

import com.sophia.cake.dao.MaterialProductDao;
import com.sophia.cake.entity.MaterialProduct;
import com.sophia.cake.service.base.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author lizhe
 * @Description description
 * @date 2020/3/6 13:00
 **/
@Service
@Slf4j
public class MaterialProductService extends BaseService<MaterialProduct> {

    private MaterialProductDao materialProductDao;
    @Autowired
    public void setMaterialProductDao(MaterialProductDao materialProductDao) {
        this.materialProductDao = materialProductDao;
    }

    @Override
    public List<MaterialProduct> query() {
        return materialProductDao.findAll();
    }

    @Override
    public MaterialProduct update(MaterialProduct materialProduct) {
        MaterialProduct search = new MaterialProduct();
        search.setId(materialProduct.getId());
        Example<MaterialProduct> example = Example.of(search);
        Optional<MaterialProduct> optional = materialProductDao.findOne(example);
        // 能够在数据库中查找到数据
        if (optional.isPresent()) {
            MaterialProduct product = optional.get();
            updateUtil.copy(materialProduct, product);
            return materialProductDao.saveAndFlush(product);
        }
        return null;
    }

    @Override
    public MaterialProduct add(MaterialProduct materialProduct) {
        log.info("materialProduct = {}", materialProduct);
        materialProduct.update();
        MaterialProduct product = materialProductDao.saveAndFlush(materialProduct);
        log.info("product = {}", product);
        return product;
    }

    @Override
    public boolean delete(MaterialProduct materialProduct) {
        try {
            materialProductDao.delete(materialProduct);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
