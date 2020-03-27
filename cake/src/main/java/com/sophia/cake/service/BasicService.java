package com.sophia.cake.service;

import com.sophia.cake.entity.po.Basic;
import com.sophia.cake.entity.po.Material;
import com.sophia.cake.entity.po.MaterialFormula;
import com.sophia.cake.entity.vo.BVo;
import com.sophia.cake.entity.vo.BasicVo;
import com.sophia.cake.entity.vo.FormulaVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.FormView;
import java.util.List;
import java.util.Set;

/**
 * @author lizhe
 * @date 2020-03-25 22:48
 */
@Slf4j
@Service
public class BasicService extends BaseService {


    public List<BVo> queryBasic() {
        return basicMapper.findBVos();
    }

    public List<BasicVo> query() {
        return basicMapper.findBasicVos();
    }

    public List<BasicVo> queryName(String name) {
        return basicMapper.findBasicVosByName("%" + name + "%");
    }


    private void updateVo(BasicVo vo) {
        float price = 0f;
        Set<FormulaVo> formulaVos = vo.getFormulas();
        for (FormulaVo formulaVo : formulaVos) {
            float formulaPrice = formulaVo.getCount() * formulaVo.getFormulaPrice();
            formulaVo.setPrice(formulaPrice);
            price += formulaPrice;
        }
        vo.setPrice(price);
    }


    @Transactional
    public void add(BasicVo basicVo) {
        updateVo(basicVo);
        int count = 0;
        count += basicMapper.addBasicVo(basicVo);
        Set<FormulaVo> materials = basicVo.getFormulas();
        count += formulaMapper.addMiddleMaterialFormulas(materials);
        checkResult(count, materials.size() + 1);
    }

    @Transactional
    public void delete(BasicVo basicVo) {
        int count = 0;
        Basic basic = basicMapper.findBasicById(basicVo.getId());
        Set<MaterialFormula> formulas = basic.getMaterialFormulaSet();
        for (MaterialFormula formula : formulas) {
            count += formulaMapper.deleteMaterialFormula(formula.getId());
        }
        count += basicMapper.deleteBasic(basicVo.getId());
        checkResult(count, formulas.size() + 1);
    }

    @Transactional
    public void update(BasicVo basicVo) {
        updateVo(basicVo);
        int count = 0;
        Set<FormulaVo> materials = basicVo.getFormulas();
        for (FormulaVo vo : materials) {
            count += formulaMapper.updateMaterialFormula(vo);
        }
        count += basicMapper.updateBasicVo(basicVo);
        checkResult(count, materials.size() + 1);
    }


}
