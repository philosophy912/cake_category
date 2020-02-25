package com.sophia.cake.service;

import com.sophia.cake.dao.BasicProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lizhe
 * @date 2020-02-25 11:31
 */
@Service
public class BasicProductService {

    private BasicProductDao basicProductDao;

    @Autowired
    public void setBasicProductDao(BasicProductDao basicProductDao) {
        this.basicProductDao = basicProductDao;
    }
}
