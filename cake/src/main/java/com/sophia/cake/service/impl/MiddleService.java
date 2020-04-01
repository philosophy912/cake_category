package com.sophia.cake.service.impl;

import com.sophia.cake.entity.bo.MiddleBo;
import com.sophia.cake.entity.vo.FormulaType;
import com.sophia.cake.entity.vo.FormulaVo;
import com.sophia.cake.entity.vo.MiddleVo;
import com.sophia.cake.service.api.BaseService;
import com.sophia.cake.service.api.IMiddleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author lizhe
 * @date 2020-03-25 22:48
 */
@Slf4j
@Service
public class MiddleService extends BaseService implements IMiddleService {


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

    @Override
    public List<MiddleVo> query() {
        List<MiddleVo> middleVos = new ArrayList<>();
        middleMapper.findMiddleBos().forEach(middleBo -> {
            MiddleVo vo = utils.convert(middleBo);
            middleVos.add(vo);
        });
        return middleVos;
    }

    @Override
    public List<MiddleVo> queryName(String name) {
        List<MiddleVo> middleVos = new ArrayList<>();
        middleMapper.findMiddleBosByName("%" + name + "%").forEach(middleBo -> {
            MiddleVo vo = utils.convert(middleBo);
            middleVos.add(vo);
        });
        return middleVos;
    }


    @Transactional
    @Override
    public void add(MiddleVo middleVo) {
        updateVo(middleVo);
        int count = 0;
        count += middleMapper.addMiddleVo(middleVo);
        Set<FormulaVo> formulas = middleVo.getFormulas();
        for (FormulaVo vo : formulas) {
            FormulaType type = FormulaType.fromValue(vo.getType());
            if (type == FormulaType.MATERIAL) {

                count += formulaMapper.addMaterialFormulaInMiddle(vo);
            } else if (type == FormulaType.BASIC) {
                count += formulaMapper.addBasicFormulaInMiddle(vo);
            }
        }
        checkResult(count, formulas.size() + 1);
    }

    @Transactional
    @Override
    public void delete(MiddleVo middleVo) {
        MiddleBo bo = middleMapper.findMiddleBo(middleVo.getId());
        if (bo == null) {
            throw new RuntimeException("not found Middle where id = " + middleVo.getId());
        } else {
            MiddleVo mvo = utils.convert(bo);
            int middleId = mvo.getId();
            log.debug("try to delete middle id = {}", middleId);
            int count = 0;
            Set<FormulaVo> formulas = mvo.getFormulas();
            for (FormulaVo vo : formulas) {
                log.debug("vo = {}", vo);
                FormulaType type = FormulaType.fromValue(vo.getType());
                if (type == FormulaType.MATERIAL) {
                    count += formulaMapper.deleteFormulaByIdInMaterialFormula(vo.getFid());
                } else if (type == FormulaType.BASIC) {
                    count += formulaMapper.deleteFormulaByIdInBasicFormula(vo.getFid());
                }
            }
            count += middleMapper.deleteMiddleById(middleId);
            checkResult(count, formulas.size() + 1);
        }

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
