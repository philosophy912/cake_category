package com.sophia.cake.service;

import com.sophia.cake.dao.BasicDao;
import com.sophia.cake.dao.BasicProductDao;
import com.sophia.cake.dao.MaterialDao;
import com.sophia.cake.dao.MaterialProductDao;
import com.sophia.cake.dao.MiddleDao;
import com.sophia.cake.entity.BasicProduct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;


/**
 * @author lizhe
 * @date 2020-03-02 22:16
 */
@Slf4j
public abstract class BaseService<T> {
    @Autowired
    protected MaterialDao materialDao;
    @Autowired
    protected BasicDao basicDao;
    @Autowired
    protected BasicProductDao basicProductDao;
    @Autowired
    protected MaterialProductDao materialProductDao;
    @Autowired
    protected MiddleDao middleDao;


    public abstract List<T> query();

    public abstract boolean update(T t);

    public abstract boolean add(T t);

    public abstract boolean delete(T t);

}
