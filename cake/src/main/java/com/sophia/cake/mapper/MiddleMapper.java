package com.sophia.cake.mapper;


import com.sophia.cake.entity.Middle;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
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
            @Result(property = "materialFormulaSet", column = "middle_id",
                    many = @Many(select = "com.sophia.cake.mapper.MaterialFormulaMapper.findById",
                            fetchType = FetchType.LAZY)),
            @Result(property = "basicFormulaSet", column = "middle_id",
                    many = @Many(select = "com.sophia.cake.mapper.BasicFormulaMapper.findById",
                            fetchType = FetchType.LAZY))
    })
    List<Middle> findAll();


}
