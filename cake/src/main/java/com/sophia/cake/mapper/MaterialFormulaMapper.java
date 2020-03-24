package com.sophia.cake.mapper;

import com.sophia.cake.entity.po.MaterialFormula;
import com.sophia.cake.entity.bo.MaterialFormulaBo;
import com.sophia.cake.entity.vo.MaterialFormulaVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.FetchType;


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

    int updateFormula(MaterialFormulaBo vo);


}
