package com.sophia.cake.mapper;

import com.sophia.cake.entity.po.Material;
import com.sophia.cake.entity.vo.MaterialVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

import static com.sophia.cake.constant.TABLE.MATERIAL;

/**
 * @author lizhe
 * @date 2020/3/11 13:09
 **/
public interface MaterialMapper {

    /**
     * 查找所有的原材料
     *
     * @return 结果
     */
    @Select("select * from " + MATERIAL)
    @Results(id = "material", value = {
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "capacity", column = "capacity"),
            @Result(property = "unit", column = "unit"),
            @Result(property = "price", column = "price"),
            @Result(property = "pricePerUnit", column = "price_per_unit")
    })
    List<Material> findAll();

    @Select("select * from " + MATERIAL)
    @Results(id = "materials", value = {
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "name", column = "name")
    })
    List<MaterialVo> findMaterials();

    /**
     * 添加原材料
     *
     * @param material 原材料
     * @return 结果
     */
    @Insert("insert into " + MATERIAL + "(NAME, CAPACITY, UNIT, PRICE, PRICE_PER_UNIT) " +
            "VALUES (#{name}, #{capacity}, #{unit}, #{price}, #{pricePerUnit})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int add(Material material);

    /**
     * 更新内容
     *
     * @param material 原材料
     * @return 结果
     */
    @Update("<script>" +
            "update " + MATERIAL +
            "<trim prefix='set' suffixOverrides=','>" +
            "<if test='name!=null'>NAME=#{name}</if>" +
            "<if test='capacity!=null'>CAPACITY=#{capacity}</if>" +
            "<if test='unit!=null'>UNIT=#{unit}</if>" +
            "<if test='price!=null'>PRICE=#{price}</if>" +
            "<if test='pricePerUnit!=null'>PRICE_PER_UNIT=#{pricePerUnit}</if>" +
            "</trim>" +
            "where id=#{id}" +
            "</script>")
    int update(Material material);

    /**
     * 删除内容
     *
     * @param id ID
     * @return 结果
     */
    @Delete("delete from " + MATERIAL + " where ID = #{id}")
    int delete(Integer id);

    /**
     * 根据ID查找
     *
     * @param id ID
     * @return 结果
     */
    @Select("select * from " + MATERIAL + " where id = #{id}")
    @ResultMap(value = "material")
    Material findById(Integer id);

    /**
     * 根据名字查
     *
     * @param name 名字
     * @return 结果
     */
    @Select("select * from " + MATERIAL + " where name = #{name}")
    @ResultMap(value = "material")
    Material findByName(String name);


}
