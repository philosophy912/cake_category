package com.sophia.cake.service;

import com.sophia.cake.dao.MaterialProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lizhe
 * @date 2020-02-25 11:31
 */
@Service
public class MaterialProductService {

    private MaterialProductDao materialProductDao;
    @Autowired
    public void setMaterialProductDao(MaterialProductDao materialProductDao) {
        this.materialProductDao = materialProductDao;
    }
}
