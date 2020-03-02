package com.sophia.cake.service;

import com.sophia.cake.dao.MiddleProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lizhe
 * @date 2020-02-25 11:32
 */
@Service
public class MiddleProductService extends BaseService {
    private MiddleProductDao middleProductDao;

    @Autowired
    public void setMiddleProductDao(MiddleProductDao middleProductDao) {
        this.middleProductDao = middleProductDao;
    }
}
