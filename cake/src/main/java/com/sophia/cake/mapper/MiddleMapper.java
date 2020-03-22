package com.sophia.cake.mapper;


import com.sophia.cake.entity.po.Middle;
import com.sophia.cake.entity.vo.MiddleVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

import static com.sophia.cake.constant.TABLE.MIDDLE;

/**
 * @author lizhe
 * @date 2020/3/11 13:09
 **/
public interface MiddleMapper {


    /**
     * 添加高级产品
     *
     * @param middle 高级产品
     * @return 结果
     */
    @Insert("insert into " + MIDDLE + "(NAME, PRICE) VALUES (#{name}, #{price})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int add(Middle middle);

    @Select("select * from " + MIDDLE)
    @Results(id = "middle", value = {
            @Result(property = "name", column = "name"),
            @Result(property = "price", column = "price"),
            @Result(property = "materialFormulaSet", column = "id",
                    many = @Many(select = "com.sophia.cake.mapper.MaterialFormulaMapper.findById",
                            fetchType = FetchType.LAZY)),
            @Result(property = "basicFormulaSet", column = "id",
                    many = @Many(select = "com.sophia.cake.mapper.BasicFormulaMapper.findById",
                            fetchType = FetchType.LAZY))
    })
    List<Middle> findAll();

    @Delete("delete from " + MIDDLE + " where id = #{id}")
    int delete(Integer id);

    @Update("<script>" +
            "update " + MIDDLE +
            "<trim prefix='set' suffixOverrides=','>" +
            "<if test='name!=null'>NAME=#{name}</if>" +
            "<if test='price!=null'>PRICE=#{price}</if>" +
            "</trim>" +
            "where id=#{id}" +
            "</script>")
    int update(Middle middle);

    List<MiddleVo> findMiddles();
}
