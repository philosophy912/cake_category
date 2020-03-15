package com.sophia.cake.mapper;

import com.sophia.cake.entity.BasicFormula;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
    @Select("select * from T_BASIC_FORMULA where id =#{id}")
    List<BasicFormula> findById(Integer id);

    /**
     * 添加初级产品
     *
     * @param basicFormula 初级产品
     * @return 结果
     */
    @Insert("insert into T_BASIC_FORMULA(COUNT, PRICE) VALUES (#{count}, #{price})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int add(BasicFormula basicFormula);

    /**
     * 更新Middle ID
     *
     * @param id       id
     * @param middleId middleId
     * @return 结果
     */
    @Update("update T_MATERIAL_FORMULA set MIDDLE_id = #{middleId} where  id = #{id}")
    int updateMiddle(@Param("id") Integer id, @Param("middleId") Integer middleId);

    /**
     * 更新BASIC ID
     *
     * @param id      id
     * @param basicId basicId
     * @return 结果
     */
    @Update("update T_MATERIAL_FORMULA set BASIC_id = #{basicId} where  id = #{id}")
    int updateBasic(@Param("id") Integer id, @Param("basicId") Integer basicId);
}
