package com.sophia.cake.mapper;

import com.sophia.cake.entity.vo.BVo;
import com.sophia.cake.entity.vo.BasicVo;
import org.apache.ibatis.annotations.Param;


import java.util.List;

/**
 * @author lizhe
 * @date 2020-04-05 22:10
 */
public interface BasicMapper {

    // 添加基础产品Vo
    int addBasicVo(BasicVo basicVo);

    // 根据ID删除基础产品
    int deleteBasicById(int id);

    // 更新基础产品Vo
    int updateBasicVo(BasicVo basicVo);

    // 更新基础产品的价格
    int updateBasicPrice(@Param("id") Integer id, @Param("price") Float price);

    // 根据基础产品的ID查找基础产品Vo
    BasicVo findBasicVoById(Integer id);

    // 查找所有的基础产品的BVo
    List<BVo> findBVos();

    // 查找所有的基础产品Vo
    List<BasicVo> findBasicVos();

    // 根据名字查找所有的基础产品Vo
    List<BasicVo> findBasicVosByName(String name);

    // 查找基础产品的数量
    int findBasicCount();

    // 根据名字查找基础产品的数量
    int findBasicByNameCount(@Param("name") String name);

    // 分页查找所有的基础产品Vo
    List<BasicVo> findPageBasicVos(@Param("index") Integer index, @Param("pageSize") Integer pageSize);

    // 根据名字分页查找所有的基础产品Vo
    List<BasicVo> findPageBasicVosByName(@Param("name") String name, @Param("index") Integer index,
                                         @Param("pageSize") Integer pageSize);
}
