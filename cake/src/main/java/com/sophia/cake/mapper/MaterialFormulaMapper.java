package com.sophia.cake.mapper;

import com.sophia.cake.entity.bo.FormulaBo;
import com.sophia.cake.entity.vo.FormulaVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lizhe
 * @date 2020-04-06 17:50
 */
public interface MaterialFormulaMapper {
    // 添加基础产品的原材料配方
    int addBasicFormulaVo(FormulaVo formulaVo);

    // 添加中级产品的原材料配方
    int addMiddleFormulaVo(FormulaVo formulaVo);

    // 根据基础产品的ID删除原材料配方
    int deleteBasicFormulaVoById(Integer basicId);

    // 根据中级产品的ID删除原材料配方
    int deleteMiddleFormulaVoById(Integer middleId);

    // 更新配方
    int updateFormulaVo(FormulaVo formulaVo);

    // 更新配方价格
    int updateFormulaPrice(@Param("id") Integer id, @Param("price") Float price);

    // 根据基础产品的ID查询原材料配方
    List<FormulaVo> findFormulaVoByBasicId(Integer basicId);

    // 根据中级产品的ID查询原材料配方
    List<FormulaVo> findFormulaVoByMiddleId(Integer middleId);

    // 根据原材料的ID查找FormulaBo对象
    List<FormulaBo> findFormulaBoByMaterialId(Integer materialId);
}
