package com.sophia.cake.mapper;

import com.sophia.cake.entity.bo.MiddleBo;
import com.sophia.cake.entity.vo.MiddleVo;

import java.util.List;

/**
 * @author lizhe
 * @date 2020-04-05 22:10
 */
public interface MiddleMapper {
    // 添加中级产品Vo
    int addMiddleVo(MiddleVo middleVo);
    // 根据ID删除中级产品
    int deleteMiddleById(int middleId);
    // 更新中级产品Vo
    int updateMiddleVo(MiddleVo middleVo);
    // 查找所有的中级产品VO的集合
    List<MiddleBo> findMiddleBos();
    // 根据名字查找所有的中级产品VO
    List<MiddleBo> findMiddleBosByName(String name);
    // 查找指定ID的中级产品VO
    MiddleBo findMiddleBo(Integer id);
}
