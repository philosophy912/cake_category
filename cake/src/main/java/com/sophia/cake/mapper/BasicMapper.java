package com.sophia.cake.mapper;

import com.sophia.cake.entity.Basic;

import java.util.List;

/**
 * @author lizhe
 * @date 2020-03-12 22:06
 */
public interface BasicMapper {

    List<Basic> findAll();

    int add(Basic basic);
}
