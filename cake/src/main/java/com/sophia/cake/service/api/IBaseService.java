package com.sophia.cake.service.api;

import java.util.List;

/**
 * @author lizhe
 * @date 2020/4/1 10:04
 **/
public interface IBaseService<T> {

    List<T> query();

    List<T> queryName(String name);

    void add(T t);

    void delete(T t);

    void update(T t);
}
