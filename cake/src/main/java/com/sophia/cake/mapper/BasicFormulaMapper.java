package com.sophia.cake.mapper;

import com.sophia.cake.entity.bo.FormulaBo;
import com.sophia.cake.entity.vo.FormulaVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lizhe
 * @date 2020-04-06 17:50
 */
public interface BasicFormulaMapper {

    // 添加中级产品的基础产品配方
    int addFormulaVo(FormulaVo formulaVo);

    // 根据中级产品的ID删除基础产品配方
    int deleteFormulaVoById(Integer middleId);

    // 更新中级产品的基础产品配方
    int updateFormulaVo(FormulaVo formulaVo);

    // 更新配方价格
    int updateFormulaPrice(@Param("id") Integer id, @Param("price") Float price);

    // 根据中级产品的ID查询基础产品配方
    List<FormulaVo> findFormulaVoById(Integer middleId);

    // 根据中级产品的ID查找FormulaBo对象
    List<FormulaBo> findFormulaBoByBasicId(Integer basicId);

}
