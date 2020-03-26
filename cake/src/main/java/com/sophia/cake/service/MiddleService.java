package com.sophia.cake.service;

import com.sophia.cake.entity.po.BasicFormula;
import com.sophia.cake.entity.po.Middle;
import com.sophia.cake.entity.vo.BasicVo;
import com.sophia.cake.entity.vo.FormulaType;
import com.sophia.cake.entity.vo.FormulaVo;
import com.sophia.cake.entity.vo.MiddleVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author lizhe
 * @date 2020-03-25 22:48
 */
@Slf4j
@Service
public class MiddleService extends BaseService {

    private void updateVo(MiddleVo vo) {
        float price = 0f;
        Set<FormulaVo> formulaVos = vo.getFormulas();
        for (FormulaVo formulaVo : formulaVos) {
            float formulaPrice = formulaVo.getCount() * formulaVo.getFormulaPrice();
            formulaVo.setPrice(formulaPrice);
            price += formulaPrice;
        }
        vo.setPrice(price);
    }


    public List<MiddleVo> query() {
        List<MiddleVo> middleVos = new ArrayList<>();
        middleMapper.findMiddleBos().forEach(middleBo -> {
            MiddleVo vo = utils.convert(middleBo);
        });
        return middleVos;
    }

    @Transactional
    public void add(MiddleVo middleVo) {
        updateVo(middleVo);
        int count = 0;
        count += middleMapper.addMiddleVo(middleVo);
        Set<FormulaVo> formulas = middleVo.getFormulas();
        for (FormulaVo vo : formulas) {
            FormulaType type = FormulaType.fromValue(vo.getType());
            if (type == FormulaType.MATERIAL) {
                count += formulaMapper.addMiddleMaterialFormula(vo);
            } else if (type == FormulaType.BASIC) {
                count += formulaMapper.addMiddleBasicFormula(vo);
            }
        }
        checkResult(count, formulas.size() + 1);
    }

    @Transactional
    public void delete(MiddleVo middleVo) {
        int count = 0;
        Set<FormulaVo> formulas = middleVo.getFormulas();
        for (FormulaVo vo : formulas) {
            FormulaType type = FormulaType.fromValue(vo.getType());
            if (type == FormulaType.MATERIAL) {
                count += formulaMapper.deleteMaterialFormula(vo.getId());
            } else if (type == FormulaType.BASIC) {
                count += formulaMapper.deleteBasicFormula(vo.getId());
            }
        }
        count += middleMapper.deleteMiddle(middleVo.getId());
        checkResult(count, formulas.size() + 1);
    }

    public void update(MiddleVo middleVo) {
        updateVo(middleVo);
        int count = 0;
        Set<FormulaVo> formulas = middleVo.getFormulas();
        for (FormulaVo vo : formulas) {
            FormulaType type = FormulaType.fromValue(vo.getType());
            if (type == FormulaType.MATERIAL) {
                count += formulaMapper.updateMaterialFormula(vo);
            } else if (type == FormulaType.BASIC) {
                count += formulaMapper.updateBasicFormula(vo);
            }
        }
        middleMapper.updateMiddleVo(middleVo);
        checkResult(count, formulas.size() + 1);
    }

}
