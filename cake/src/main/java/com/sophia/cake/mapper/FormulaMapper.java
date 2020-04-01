package com.sophia.cake.mapper;

import com.sophia.cake.entity.po.BasicFormula;
import com.sophia.cake.entity.po.MaterialFormula;
import com.sophia.cake.entity.vo.FormulaVo;

import java.util.Set;

/**
 * @author lizhe
 * @date 2020/3/25 14:50
 **/
public interface FormulaMapper {
    // 在原材料配方表中根据ID查找配方
    BasicFormula findBasicFormulaById(Integer id);

    // 在基础产品配方表中根据ID查找配方
    MaterialFormula findMaterialFormulaById(Integer id);

    // 在原材料配方表中根据配方ID查找配方
    FormulaVo findFormulaByIdInMaterialFormula(Integer id);

    // 在基础产品配方表中根据配方ID查找配方
    FormulaVo findFormulaByIdInBasicFormula(Integer id);

    // 在原材料配方表中根据中级产品的ID查找配方
    FormulaVo findFormulaByMiddleIdInMaterialFormula(Integer id);

    // 在原材料配方表中根据基础产品的ID查找配方
    FormulaVo findFormulaByBasicIdInMaterialFormula(Integer id);

    // 在基础产品配方表中根据中级产品的ID查找配方
    FormulaVo findFormulaByMiddleIdInBasicFormula(Integer id);

    // 添加原材料配方到基础产品中
    int addMaterialFormulaInBasic(FormulaVo formulaVo);

    // 添加基础产品配方到中级产品中
    int addBasicFormulaInMiddle(FormulaVo formulaVo);

    // 添加原材料配方到中级产品中
    int addMaterialFormulaInMiddle(FormulaVo formulaVo);

    // 批量添加原材料配方到基础产品中
    int addMaterialFormulasInBasic(Set<FormulaVo> formulaVos);

    // 批量添加基础产品配方到中级产品中
    int addBasicFormulasInMiddle(Set<FormulaVo> formulaVos);

    // 批量添加原材料配方到中级产品中
    int addMaterialFormulasInMiddle(Set<FormulaVo> formulaVos);

    // 更新原材料配方
    int updateMaterialFormula(FormulaVo formulaVo);

    // 更新基础产品配方
    int updateBasicFormula(FormulaVo formulaVo);

    // 在原材料配方表中根据配方ID删除配方
    int deleteFormulaByIdInMaterialFormula(Integer id);

    // 在基础产品配方表中根据配方ID删除配方
    int deleteFormulaByIdInBasicFormula(Integer id);

    // 在原材料配方表中根据基础产品ID删除配方
    int deleteFormulaByBasicIdInMaterialFormula(Integer id);

    // 在原材料配方表中根据中级产品ID删除配方
    int deleteFormulaByMiddleIdInMaterialFormula(Integer id);

    // 在基础产品配方表中根据中级产品ID删除配方
    int deleteFormulaByMiddleIdInBasicFormula(Integer id);

}
