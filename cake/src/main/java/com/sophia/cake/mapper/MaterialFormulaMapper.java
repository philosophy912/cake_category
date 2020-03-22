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

import static com.sophia.cake.constant.TABLE.MATERIAL;
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
    @Select("select * from " + MATERIAL_FORMULA + " where basic_id =#{basicId}")
    @Results(id = "materialFormula", value = {
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "count", column = "count"),
            @Result(property = "price", column = "price"),
            @Result(property = "material", column = "basic_id",
                    many = @Many(select = "com.sophia.cake.mapper.MaterialMapper.findById",
                            fetchType = FetchType.LAZY))
    })
    List<MaterialFormula> findById(Integer basicId);


//    @Select("select f. from " + MATERIAL_FORMULA + " as f left join " +
//            MATERIAL + " AS m on f.material_id = m.id where basic_id =#{basicId}")
//    @Results(id = "materialFormulaVo",value = {
//            @Result(id = true, property = "id", column = "id")
//    })
//    List<MaterialFormulaVo> findVoById(Integer basicId);


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


    @Delete("delete from " + MATERIAL_FORMULA + " where basic_id = #{id}")
    int deleteBasic(Integer id);

    @Delete("delete from " + MATERIAL_FORMULA + " where middle_id = #{id}")
    int deleteMiddle(Integer id);

    @Update("<script>" +
            "update " + MATERIAL_FORMULA +
            "<trim prefix='set' suffixOverrides=','>" +
            "<if test='count!=null'>COUNT=#{count}</if>" +
            "<if test='price!=null'>PRICE=#{price}</if>" +
            "<if test='materialId!=null'>MATERIAL_ID=#{materialId}</if>" +
            "<if test='middleId!=null'>MIDDLE_ID=#{middleId}</if>" +
            "<if test='basicId!=null'>BASIC_ID=#{basicId}</if>" +
            "</trim>" +
            "where id=#{id}" +
            "</script>")
    int updateFormula(MaterialFormulaBo vo);


}
