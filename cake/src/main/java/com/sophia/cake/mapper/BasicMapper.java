package com.sophia.cake.mapper;

import com.sophia.cake.entity.po.Basic;
import com.sophia.cake.entity.vo.BasicVo;

import java.util.List;

/**
 * @author lizhe
 * @date 2020-03-12 22:06
 */
public interface BasicMapper {
    /**
     * 查找所有的基础产品
     *
     * @return 结果
     */
    List<Basic> findAll();

    /**
     * todo 故意用name取代unit和count
     *
     * @return 集合
     */
    List<BasicVo> findBasicVos();

    List<BasicVo> findBasics();

    Basic findByName(String name);

    Basic findById(Integer id);

    Integer add(Basic basic);

    int delete(Integer id);

    int update(Basic basic);

}
