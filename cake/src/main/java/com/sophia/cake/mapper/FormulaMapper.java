package com.sophia.cake.mapper;

import com.sophia.cake.entity.vo.FormulaVo;

import java.util.Set;

/**
 * @author lizhe
 * @date 2020-04-05 22:10
 */
public interface FormulaMapper {
    // 批量添加原材料配方到基础产品中
    int addMaterialFormulasInBasic(Set<FormulaVo> formulas);
    // 在原材料配方表中根据配方ID删除配方
    int deleteFormulaByIdInMaterialFormula(Integer id);
    // 更新原材料配方
    int updateMaterialFormula(FormulaVo vo);
    // 添加原材料配方到中级产品中
    int addMaterialFormulaInMiddle(FormulaVo vo);
    // 添加基础产品配方到中级产品中
    int addBasicFormulaInMiddle(FormulaVo vo);
    // 在基础产品配方表中根据配方ID删除配方
    int deleteFormulaByIdInBasicFormula(Integer id);
    // 更新基础产品配方
    int updateBasicFormula(FormulaVo vo);
    // 在原材料配方表中根据中级产品的ID查找配方
    FormulaVo findFormulaByMiddleIdInMaterialFormula(Integer middleId);
    // 在基础产品配方表中根据中级产品的ID查找配方
    FormulaVo findFormulaByMiddleIdInBasicFormula(Integer middleId);
    // 在原材料配方表中根据基础产品的ID查找配方
    FormulaVo findFormulaByBasicIdInMaterialFormula(Integer basicId);
}
