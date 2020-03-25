package com.sophia.cake.service;

import com.sophia.cake.entity.vo.BasicVo;
import com.sophia.cake.entity.vo.FormulaVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * @author lizhe
 * @date 2020-03-25 22:48
 */
@Slf4j
@Service
public class BasicService extends BaseService {

    public List<BasicVo> query() {
        return basicMapper.findBasicVos();
    }

    @Transactional
    public void add(BasicVo basicVo) {
        Integer id = basicMapper.addBasicVo(basicVo);
        Set<FormulaVo> materials = basicVo.getMaterials();
        materials.forEach(formulaVo -> formulaMapper.addBasicMaterialFormula(formulaVo));
    }

}
