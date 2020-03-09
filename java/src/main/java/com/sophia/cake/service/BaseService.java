package com.sophia.cake.service;

import com.sophia.cake.dao.MaterialDao;
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


    public abstract List<T> query();

    public abstract boolean update(T t) throws IOException;

    public abstract boolean add(T t);

    public abstract boolean delete(T t);

}
