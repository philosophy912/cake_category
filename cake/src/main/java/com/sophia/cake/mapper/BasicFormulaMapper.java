package com.sophia.cake.mapper;

import com.sophia.cake.entity.po.BasicFormula;
import com.sophia.cake.entity.bo.BasicFormulaBo;
import org.apache.ibatis.annotations.Param;
import java.util.List;


/**
 * @author lizhe
 * @date 2020-03-14 21:38
 */
public interface BasicFormulaMapper {
    /**
     * 查询所有的基础产品
     *
     * @param id id
     * @return 结果
     */
    List<BasicFormula> findById(Integer id);

    /**
     * 添加初级产品
     *
     * @param basicFormula 初级产品
     * @return 结果
     */
    int add(BasicFormula basicFormula);

    /**
     * 更新Middle ID
     *
     * @param id       id
     * @param middleId middleId
     * @return 结果
     */
    int updateMiddle(@Param("id") Integer id, @Param("middleId") Integer middleId);

    /**
     * 更新BASIC ID
     *
     * @param id      id
     * @param basicId basicId
     * @return 结果
     */
    int updateBasic(@Param("id") Integer id, @Param("basicId") Integer basicId);

    /**
     * 根据middle id删除
     *
     * @param id id
     * @return 结果
     */
    int deleteMiddle(Integer id);

    /**
     * 更新Formula
     * @param convert bo
     * @return
     */
    int updateFormula(BasicFormulaBo convert);
}
