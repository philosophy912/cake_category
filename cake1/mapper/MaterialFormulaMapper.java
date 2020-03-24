package com.sophia.cake.mapper;

import com.sophia.cake.entity.po.MaterialFormula;
import org.apache.ibatis.annotations.Param;


import java.util.List;

import static com.sophia.cake.constant.TABLE.MATERIAL_FORMULA;

/**
 * @author lizhe
 * @date 2020-03-14 21:38
 */
public interface MaterialFormulaMapper {
    /**
     * 根据 basicId 查询MaterialFormula
     *
     * @param basicId 配方ID
     * @return 结果
     */
    List<MaterialFormula> findById(Integer basicId);


    /**
     * 添加原料产品
     *
     * @param formula 原料产品
     * @return 结果
     */
    int add(MaterialFormula formula);

    /**
     * 更新Material ID
     *
     * @param id         id
     * @param materialId materialId
     * @return 结果
     */
    int updateMaterial(@Param("id") Integer id, @Param("materialId") Integer materialId);

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


    int deleteBasic(Integer id);

    int deleteMiddle(Integer id);



}
