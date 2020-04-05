package com.sophia.cake.mapper;

import com.sophia.cake.entity.vo.BVo;
import com.sophia.cake.entity.vo.BasicVo;

import java.util.List;

/**
 * @author lizhe
 * @date 2020-04-05 22:10
 */
public interface BasicMapper {
    // 根据基础产品的ID查找基础产品Vo
    BasicVo findBasicVoById(Integer id);
    // 查找所有的基础产品的BVo
    List<BVo> findBVos();
    // 查找所有的基础产品Vo
    List<BasicVo> findBasicVos();
    // 查找所有的基础产品Vo
    List<BasicVo> findBasicVosByName(String name);
    // 添加基础产品Vo
    int addBasicVo(BasicVo basicVo);
    // 根据ID删除基础产品
    int deleteBasicById(int id);
    // 更新基础产品Vo
    int updateBasicVo(BasicVo basicVo);
}
