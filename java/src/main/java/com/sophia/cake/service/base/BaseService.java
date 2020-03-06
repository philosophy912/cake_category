package com.sophia.cake.service.base;

import com.sophia.cake.utils.UpdateUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.List;


/**
 * @author lizhe
 * @date 2020-03-02 22:16
 */
@Slf4j
public abstract class BaseService<T> {
    protected UpdateUtil updateUtil;

    public abstract List<T> query();

    public abstract T update(T t);

    public abstract T add(T t);

    public abstract boolean delete(T t);

}
