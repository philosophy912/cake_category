package com.sophia.cake.mapper;

import com.sophia.cake.entity.bo.MiddleBo;
import com.sophia.cake.entity.vo.MiddleVo;
import org.apache.ibatis.annotations.Param;

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

    // 更新中级产品的价格
    int updateMiddlePrice(@Param("id") Integer id, @Param("price") Float price);

    // 查找所有的中级产品VO的集合
    List<MiddleBo> findMiddleBos();

    // 根据名字查找所有的中级产品VO
    List<MiddleBo> findMiddleBosByName(String name);

    // 查找指定ID的中级产品VO
    MiddleBo findMiddleBoById(Integer id);

    // 查找中级产品的数量
    int findMiddleCount();

    // 根据名字查找中级产品的数量
    int findBasicByNameCount(@Param("name") String name);

    // 查找所有的中级产品VO的集合
    List<MiddleBo> findPageMiddleBos(@Param("index") Integer index, @Param("pageSize") Integer pageSize);

    // 根据名字查找所有的中级产品VO
    List<MiddleBo> findPageMiddleBosByName(@Param("name") String name, @Param("index") Integer index,
                                           @Param("pageSize") Integer pageSize);
}
