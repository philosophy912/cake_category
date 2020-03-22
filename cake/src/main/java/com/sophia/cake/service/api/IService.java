package com.sophia.cake.service.api;

import java.util.List;

/**
 * @author lizhe
 * @date 2020-03-21 19:17
 */
public interface IService<T> {

    List<T> findAll();

    boolean add(T t);

    boolean delete(Integer id);

    boolean update(T t);
}

