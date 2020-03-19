package com.sophia.cake.service.api;

import java.util.List;

/**
 * @author lizhe
 * @date 2020/3/12 15:46
 **/
public interface IService<T> {

    List<T> findAll();

    boolean add(T t);

    boolean delete(Integer id);

    boolean update(T t);
}
