package com.sophia.cake.mapper;

import com.sophia.cake.entity.Basic;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

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
    @Select("select * from T_BASIC")
    @Results(id = "basic", value = {
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "price", column = "price"),
            @Result(property = "materialFormulaSet", column = "basic_id",
                    many = @Many(select = "com.sophia.cake.mapper.MaterialFormulaMapper.findById",
                            fetchType = FetchType.LAZY))
    })
    List<Basic> findAll();

    /**
     * 添加基础产品
     *
     * @param basic 基础产品
     * @return 结果
     */
    @Insert("insert into T_BASIC(NAME, PRICE) VALUES (#{name}, #{price})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Integer add(Basic basic);
}
