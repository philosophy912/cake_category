package com.sophia.cake.service.entity;

import com.sophia.cake.dao.BasicProductDao;
import com.sophia.cake.entity.BasicProduct;
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
public class BasicProductService extends BaseService<BasicProduct> {

    private BasicProductDao basicProductDao;
    @Autowired
    public void setBasicProductDao(BasicProductDao basicProductDao) {
        this.basicProductDao = basicProductDao;
    }

    @Override
    public List<BasicProduct> query() {
        return basicProductDao.findAll();
    }

    @Override
    public BasicProduct update(BasicProduct basicProduct) {
        BasicProduct search = new BasicProduct();
        search.setId(basicProduct.getId());
        Example<BasicProduct> example = Example.of(search);
        Optional<BasicProduct> optional = basicProductDao.findOne(example);
        // 能够在数据库中查找到数据
        if (optional.isPresent()) {
            BasicProduct product = optional.get();
            updateUtil.copy(basicProduct, product);
            return basicProductDao.saveAndFlush(product);
        }
        return null;
    }

    @Override
    public BasicProduct add(BasicProduct basicProduct) {
        log.info("basicProduct = {}", basicProduct);
        basicProduct.update();
        BasicProduct product = basicProductDao.saveAndFlush(basicProduct);
        log.info("product = {}", product);
        return product;
    }

    @Override
    public boolean delete(BasicProduct basicProduct) {
        try {
            basicProductDao.delete(basicProduct);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
