package com.sophia.cake.mapper;

import com.sophia.cake.entity.po.Basic;
import com.sophia.cake.entity.vo.BVo;
import com.sophia.cake.entity.vo.BasicVo;

import java.util.List;

/**
 * @author lizhe
 * @date 2020-03-24 22:35
 */
public interface BasicMapper {
    // 查找所有的基础产品
    List<Basic> findBasics();
    // 根据ID查找基础产品
    Basic findBasicById(Integer id);
    // 根据ID查找基础产品
    Basic findBasicByName(String name);
    // 添加基础产品
    int addBasic(Basic basic);
    // 更新基础产品
    int updateBasic(Basic basic);
    // 添加基础产品
    int addBasicVo(BasicVo basicVo);
    // 更新基础产品
    int updateBasicVo(BasicVo basicVo);
    // 根据ID删除基础产品
    int deleteBasic(Integer id);
    // 查找所有的基础产品VO
    List<BasicVo> findBasicVos();
    // 查找所有的基础产品VO
    List<BasicVo> findBasicVosByName(String name);
    // 查找所有的基础产品的BVo
    List<BVo> findBVos();
}
