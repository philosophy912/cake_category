package com.sophia.cake.service;

import java.util.List;

/**
 * @author lizhe
 * @date 2020/3/12 15:46
 **/
public interface IService<T> {

    public List<T> findAll();

    public boolean add(T t);

    public boolean delete(Integer id);

    public boolean update(T t);
}
