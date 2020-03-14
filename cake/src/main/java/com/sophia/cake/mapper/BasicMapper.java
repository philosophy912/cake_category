package com.sophia.cake.mapper;

import com.sophia.cake.entity.Basic;
import com.sophia.cake.entity.Material;

import java.util.List;

/**
 * @author lizhe
 * @date 2020-03-12 22:06
 */
public interface BasicMapper {
    /**
     * 查找所有的基础产品
     * @return 结果
     */
    List<Basic> findAll();

    /**
     * 添加基础产品
     * @param basic  基础产品
     * @return 结果
     */
    int add(Basic basic);

    /**
     * 根据ID查找
     * @param id  ID
     * @return 结果
     */
    Material findById(Integer id);

}
