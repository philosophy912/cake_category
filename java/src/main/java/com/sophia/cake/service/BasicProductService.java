package com.sophia.cake.service;

import com.sophia.cake.dao.BasicProductDao;
import com.sophia.cake.entity.Basic;
import com.sophia.cake.entity.BasicProduct;
import com.sophia.cake.entity.Material;
import com.sophia.cake.entity.MaterialProduct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author lizhe
 * @date 2020-02-25 11:31
 */
@Service
@Slf4j
public class BasicProductService extends BaseService {

    private BasicProductDao basicProductDao;

    @Autowired
    public void setBasicProductDao(BasicProductDao basicProductDao) {
        this.basicProductDao = basicProductDao;
    }


    public List<Basic> queryBasics(){
        return basicProductDao.findAll();
    }

    public Boolean updateBasic(Basic basicProduct){
        Basic searchBasic = new Basic();
        searchBasic.setId(basicProduct.getId());
        Example<Basic> example = Example.of(searchBasic);
        Optional<Basic> basicProductOptional = basicProductDao.findOne(example);
        if(basicProductOptional.isPresent()){
            Basic product = basicProductOptional.get();
            updateUtil.copy(basicProduct, product);
            basicProductDao.saveAndFlush(product);
            return true;
        }else{
            return false;
        }
    }

    public Basic add(Basic basic) {
        Basic product = null;
        log.info("Basic = {}", basic);
        basic.update();
        product = basicProductDao.saveAndFlush(basic);
        log.info("product = {}", product);
        return product;
    }
}
