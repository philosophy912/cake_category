package com.sophia.cake.mapper;

import com.sophia.cake.entity.BasicFormula;
import com.sophia.cake.entity.MaterialFormula;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
     * @param basicId 基础产品ID
     * @return 结果
     */
    @Select("select * from " + MATERIAL_FORMULA + " where id =#{id}")
    List<MaterialFormula> findById(Integer basicId);

    /**
     * 添加原料产品
     *
     * @param formula 原料产品
     * @return 结果
     */
    @Insert("insert into " + MATERIAL_FORMULA + "(COUNT, PRICE) VALUES (#{count}, #{price})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int add(MaterialFormula formula);

    /**
     * 更新Material ID
     *
     * @param id         id
     * @param materialId materialId
     * @return 结果
     */
    @Update("update " + MATERIAL_FORMULA + " set MATERIAL_ID = #{materialId} where  id = #{id}")
    int updateMaterial(@Param("id") Integer id, @Param("materialId") Integer materialId);

    /**
     * 更新Middle ID
     *
     * @param id       id
     * @param middleId middleId
     * @return 结果
     */
    @Update("update " + MATERIAL_FORMULA + " set MIDDLE_id = #{middleId} where  id = #{id}")
    int updateMiddle(@Param("id") Integer id, @Param("middleId") Integer middleId);

    /**
     * 更新BASIC ID
     *
     * @param id      id
     * @param basicId basicId
     * @return 结果
     */
    @Update("update " + MATERIAL_FORMULA + " set BASIC_id = #{basicId} where  id = #{id}")
    int updateBasic(@Param("id") Integer id, @Param("basicId") Integer basicId);


}
