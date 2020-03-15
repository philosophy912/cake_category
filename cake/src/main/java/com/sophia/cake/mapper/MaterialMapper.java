package com.sophia.cake.mapper;

import com.sophia.cake.entity.Material;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

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
    @Select("select * from T_MATERIAL")
    List<Material> findAll();

    /**
     * 添加原材料
     *
     * @param material 原材料
     * @return 结果
     */
    @Insert("insert into T_MATERIAL(NAME, CAPACITY, UNIT, PRICE, PRICE_PER_UNIT) " +
            "VALUES (#{name}, #{capacity}, #{unit}, #{price}, #{pricePerUnit})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int add(Material material);

    /**
     * 更新内容
     *
     * @param material 原材料
     * @return 结果
     */
    @Update("<script>" +
            "update T_MATERIAL" +
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
    @Delete("delete from T_MATERIAL where ID = #{id}")
    int delete(Integer id);

    /**
     * 根据ID查找
     *
     * @param id ID
     * @return 结果
     */
    @Select("select * from T_MATERIAL where id = #{id}")
    Material findById(Integer id);

    /**
     * 根据名字查
     *
     * @param name 名字
     * @return 结果
     */
    @Select("select * from T_MATERIAL where name = #{name}")
    Material findByName(String name);

}
