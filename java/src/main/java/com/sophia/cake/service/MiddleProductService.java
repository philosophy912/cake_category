package com.sophia.cake.service;

import com.sophia.cake.dao.MiddleProductDao;
import com.sophia.cake.entity.Basic;
import com.sophia.cake.entity.BasicProduct;
import com.sophia.cake.entity.Material;
import com.sophia.cake.entity.MaterialProduct;
import com.sophia.cake.entity.Middle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author lizhe
 * @date 2020-02-25 11:32
 */
@Service
@Slf4j
public class MiddleProductService extends BaseService {
    private MiddleProductDao middleProductDao;

    @Autowired
    public void setMiddleProductDao(MiddleProductDao middleProductDao) {
        this.middleProductDao = middleProductDao;
    }

    public Middle add(Middle middle) {
        Middle product = null;
        log.info("Middle = {}", middle);
        middle.update();
        product = middleProductDao.saveAndFlush(middle);
        return product;
    }
}
