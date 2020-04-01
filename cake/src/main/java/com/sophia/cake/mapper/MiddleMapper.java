package com.sophia.cake.mapper;

import com.sophia.cake.entity.bo.MiddleBo;
import com.sophia.cake.entity.po.Middle;
import com.sophia.cake.entity.vo.MiddleVo;

import java.util.List;

/**
 * 查询出来的都是BO，而不是VO
 * @author lizhe
 * @date 2020-03-24 22:36
 */
public interface MiddleMapper {
    // 查找所有的中级产品
    List<Middle> findMiddles();

    // 根据ID查找中级产品
    Middle findMiddleById(Integer id);

    // 查找指定ID的中级产品VO
    MiddleBo findMiddleBo(Integer id);

    // 查找所有的中级产品VO的集合
    List<MiddleBo> findMiddleBos();

    // 根据名字查找所有的中级产品VO
    List<MiddleBo> findMiddleBosByName(String name);

    // 添加中级产品
    int addMiddle(Middle middle);

    // 添加中级产品Vo
    int addMiddleVo(MiddleVo middleVo);

    // 更新中级产品
    int updateMiddle(Middle middle);

    // 更新中级产品Vo
    int updateMiddleVo(MiddleVo middleVo);

    // 根据ID删除中级产品
    int deleteMiddleById(Integer id);




}
