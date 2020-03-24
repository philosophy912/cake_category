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

    int add(Middle middle);

    List<Middle> findAll();

    int delete(Integer id);

    int update(Middle middle);

    List<MiddleVo> findMiddles();
}
