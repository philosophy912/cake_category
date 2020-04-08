package com.sophia.cake.service.impl;

import com.philosophy.base.common.Pair;
import com.philosophy.base.entity.EnvData;
import com.sophia.cake.entity.bo.EntityBo;
import com.sophia.cake.entity.bo.NameBo;
import com.sophia.cake.entity.po.Material;
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
    public Pair<List<BasicVo>, EnvData> query(EntityBo entityBo) {
        int index = entityBo.getEnvData().getPageNo() - 1;
        int pageSize = entityBo.getEnvData().getPageSize();
        int totalRows = basicMapper.findBasicCount();
        int totalPages = utils.getTotalPages(totalRows, pageSize);
        List<BasicVo> basicVos = basicMapper.findPageBasicVos(index, pageSize);
        return new Pair<>(basicVos, getEnvData(index, pageSize, totalRows, totalPages));
    }


    @Override
    public Pair<List<BasicVo>, EnvData> queryName(NameBo nameBo) {
        String name = "%" + nameBo.getName() + "%";
        int index = nameBo.getEnvData().getPageNo() - 1;
        int pageSize = nameBo.getEnvData().getPageSize();
        int totalRows = basicMapper.findBasicByNameCount(name);
        int totalPages = utils.getTotalPages(totalRows, pageSize);
        List<BasicVo> basicVos = basicMapper.findPageBasicVosByName(name, index, pageSize);
        return new Pair<>(basicVos, getEnvData(index, pageSize, totalRows, totalPages));
    }


    @Transactional
    @Override
    public void add(BasicVo basicVo) {
        updateBasicVo(basicVo);
        checkResult(1, basicMapper.addBasicVo(basicVo));
        int pid = basicVo.getId();
        basicVo.getFormulas().stream()
                .peek(formulaVo -> formulaVo.setPid(pid))
                .forEach(formulaVo ->
                        checkResult(1, materialFormulaMapper.addBasicFormulaVo(formulaVo)));
    }

    @Transactional
    @Override
    public void delete(BasicVo basicVo) {
        BasicVo basic = basicMapper.findBasicVoById(basicVo.getId());
        if (basic == null) {
            throw new RuntimeException("not found Basic where id = " + basicVo.getId());
        } else {
            int basicId = basic.getId();
            basic.getFormulas()
                    .forEach(formulaVo -> checkResult(1, materialFormulaMapper.deleteBasicFormulaVoById(basicId)));
            checkResult(1, basicMapper.deleteBasicById(basicId));
        }

    }

    @Transactional
    @Override
    public void update(BasicVo basicVo) {
        float totalPrice = 0f;
        int id = basicVo.getId();
        log.debug("basicId = {}", id);
        BasicVo vo = basicMapper.findBasicVoById(id);
        if (null != vo) {
            Set<FormulaVo> formulas = vo.getFormulas();
            // 调整数量或者原材料类别导致调整价格
            for (FormulaVo formulaVo : formulas) {
                int materialId = formulaVo.getId();
                log.debug("materialId = {}", materialId);
                // 考虑到原材料类别也可能随之变化，所以先查找原材料
                Material material = materialMapper.findMaterialById(materialId);
                // 然后计算总价
                float price = formulaVo.getCount() * material.getPricePerUnit();
                formulaVo.setPrice(price);
                totalPrice += price;
                checkResult(1, materialFormulaMapper.updateFormulaVo(formulaVo));
            }
            // 设置计算后的价格
            basicVo.setPrice(totalPrice);
            checkResult(1, basicMapper.updateBasicVo(basicVo));
        } else {
            throw new RuntimeException("basic[" + id + "] not found ");
        }
    }


}
