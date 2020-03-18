package com.sophia.cake.mapper;

import com.sophia.cake.entity.Basic;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.FetchType;

import static com.sophia.cake.constant.TABLE.BASIC;

import java.util.List;
import java.util.Set;

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
    @Select("select * from " + BASIC)
    @Results(id = "basic", value = {
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "price", column = "price"),
            @Result(property = "materialFormulaSet", javaType = Set.class, column = "id",
                    many = @Many(select = "com.sophia.cake.mapper.MaterialFormulaMapper.findById",
                            fetchType = FetchType.LAZY))
    })
    List<Basic> findAll();

    @Select("select * from " + BASIC + " where NAME = #{name}")
    @ResultMap(value = "basic")
    Basic findByName(String name);

    @Select("select * from " + BASIC + " where id = #{id}")
    @ResultMap(value = "basic")
    Basic findById(Integer id);

    /**
     * 添加基础产品
     *
     * @param basic 基础产品
     * @return 结果
     */
    @Insert("insert into " + BASIC + "(NAME, PRICE) VALUES (#{name}, #{price})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    Integer add(Basic basic);


    @Delete("delete from " + BASIC + "where id = #{id}")
    int delete(Integer id);

    @Update("<script>" +
            "update " + BASIC +
            "<trim prefix='set' suffixOverrides=','>" +
            "<if test='name!=null'>NAME=#{name}</if>" +
            "<if test='price!=null'>PRICE=#{price}</if>" +
            "</trim>" +
            "where id=#{id}" +
            "</script>")
    int update(Basic basic);
}
