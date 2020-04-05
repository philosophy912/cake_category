package com.sophia.cake.service.impl;

import com.sophia.cake.entity.vo.BVo;
import com.sophia.cake.entity.vo.BasicVo;
import com.sophia.cake.entity.vo.FormulaVo;
import com.sophia.cake.service.api.BaseService;
import com.sophia.cake.service.api.IBasicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author lizhe
 * @date 2020-03-25 22:48
 */
@Slf4j
@Service
public class BasicService extends BaseService implements IBasicService {

    @Override
    public List<BVo> queryBasic() {
        return basicMapper.findBVos();
    }

    @Override
    public List<BasicVo> query() {
        return basicMapper.findBasicVos();
    }

    @Override
    public List<BasicVo> queryName(String name) {
        return basicMapper.findBasicVosByName("%" + name + "%");
    }


    @Transactional
    @Override
    public void add(BasicVo basicVo) {
        updateBasicVo(basicVo);
        int count = 0;
        count += basicMapper.addBasicVo(basicVo);
        int pid = basicVo.getId();
        Set<FormulaVo> formulas = basicVo.getFormulas().stream()
                .peek(formulaVo -> formulaVo.setPid(pid))
                .collect(Collectors.toSet());
        count += formulaMapper.addMaterialFormulasInBasic(formulas);
        checkResult(count, formulas.size() + 1);
    }

    @Transactional
    @Override
    public void delete(BasicVo basicVo) {
        int count = 0;
        BasicVo basic = basicMapper.findBasicVoById(basicVo.getId());
        if (basic == null) {
            throw new RuntimeException("not found Basic where id = " + basicVo.getId());
        } else {
            int basicId = basic.getId();
            Set<FormulaVo> formulas = basic.getFormulas();
            for (FormulaVo formula : formulas) {
                count += formulaMapper.deleteFormulaByIdInMaterialFormula(formula.getFid());
            }
            count += basicMapper.deleteBasicById(basicId);
            checkResult(count, formulas.size() + 1);
        }

    }

    @Transactional
    @Override
    public void update(BasicVo basicVo) {
        updateBasicVo(basicVo);
        int count = 0;
        Set<FormulaVo> materials = basicVo.getFormulas();
        for (FormulaVo vo : materials) {
            count += formulaMapper.updateMaterialFormula(vo);
        }
        count += basicMapper.updateBasicVo(basicVo);
        checkResult(count, materials.size() + 1);
    }


}
