package com.sophia.cake.mapper;

import com.sophia.cake.entity.po.Middle;

import java.util.List;

/**
 * @author lizhe
 * @date 2020-03-24 22:36
 */
public interface MiddleMapper {
    // 查找所有的中级产品
    List<Middle> findMiddles();
    // 根据ID查找中级产品
    Middle findMiddleById(Integer id);
    // 添加中级产品
    int addMiddle(Middle middle);
    // 更新中级产品
    int updateMiddle(Middle middle);
    // 根据ID删除中级产品
    int deleteMiddle(Integer id);




}
