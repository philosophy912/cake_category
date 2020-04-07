package com.sophia.cake.service.api;

import com.philosophy.base.common.Pair;
import com.sophia.cake.entity.bo.FormulaBo;
import com.sophia.cake.entity.po.Material;
import com.sophia.cake.entity.vo.BasicVo;
import com.sophia.cake.entity.FormulaType;
import com.sophia.cake.entity.vo.FormulaVo;
import com.sophia.cake.entity.vo.MiddleVo;
import com.sophia.cake.mapper.BasicFormulaMapper;
import com.sophia.cake.mapper.BasicMapper;
import com.sophia.cake.mapper.MaterialFormulaMapper;
import com.sophia.cake.mapper.MaterialMapper;
import com.sophia.cake.mapper.MiddleMapper;
import com.sophia.cake.utils.EntityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author lizhe
 * @date 2020-03-25 22:47
 */
@Slf4j
public abstract class BaseService {

    @Resource
    protected BasicMapper basicMapper;
    @Resource
    protected MaterialMapper materialMapper;
    @Resource
    protected MiddleMapper middleMapper;
    @Resource
    protected BasicFormulaMapper basicFormulaMapper;
    @Resource
    protected MaterialFormulaMapper materialFormulaMapper;
    @Autowired
    protected EntityUtils utils;

    protected void checkResult(int expect, int actual) {
        log.debug("expect is {} and actual is {}", expect, actual);
        if (expect != actual) {
            throw new RuntimeException("operate sql failed");
        }
    }

    /**
     * 更新Material表
     *
     * @param material material对象
     * @return 更新后的单位价格
     */
    protected Float updateMaterial(Material material) {
        Float pricePerUnit = material.getPrice() / material.getCapacity();
        material.setPricePerUnit(pricePerUnit);
        checkResult(1, materialMapper.updateMaterial(material));
        return pricePerUnit;
    }

    /**
     * 根据Material ID找出受到影响的BasicId和MiddleId
     *
     * @param materialId 原材料ID
     * @return 影响的BasicId和MiddleId
     */
    protected Pair<Set<Integer>, Set<Integer>> updateMaterialFormula(Integer materialId, Float materialPrice) {
        int count = 0;
        // 影响的基础产品
        Set<Integer> basicIds = new HashSet<>();
        // 影响的中级产品
        Set<Integer> middleIds = new HashSet<>();
        List<FormulaBo> formulaBos = materialFormulaMapper.findFormulaBoByMaterialId(materialId);
        for (FormulaBo formulaBo : formulaBos) {
            if (formulaBo.getBasic_id() != null) {
                basicIds.add(formulaBo.getBasic_id());
            }
            if (formulaBo.getMiddle_id() != null) {
                middleIds.add(formulaBo.getMiddle_id());
            }
            // 对应的配方表的ID
            int id = formulaBo.getId();
            float formulaCount = formulaBo.getCount();
            float price = materialPrice * formulaCount;
            count += materialFormulaMapper.updateFormulaPrice(id, price);
        }
        checkResult(formulaBos.size(), count);
        return new Pair<>(basicIds, middleIds);
    }

    /**
     * 更新Basic
     *
     * @param pair 包含了影响的BasicId和MiddleId
     * @return 受到影响的MiddleId
     */
    protected Set<Integer> updateBasic(Pair<Set<Integer>, Set<Integer>> pair) {
        int count = 0;
        int expect = 0;
        Set<Integer> basicIds = pair.getFirst();
        Set<Integer> middleIds = pair.getSecond();
        for (Integer id : basicIds) {
            float price = 0f;
            List<FormulaVo> formulaVos = materialFormulaMapper.findFormulaVoByBasicId(id);
            for (FormulaVo vo : formulaVos) {
                price += vo.getPrice();
            }
            count += basicMapper.updateBasicPrice(id, price);
            updateBasicFormula(id, price);
            // 根据中级产品查询到影响的Middle产品
            List<FormulaBo> bos = basicFormulaMapper.findFormulaBoByBasicId(id);
            for (FormulaBo bo : bos) {
                middleIds.add(bo.getMiddle_id());
            }
        }
        checkResult(expect, count);
        return middleIds;
    }

    /**
     * 更新基础材料配方表
     *
     * @param basicId    基础材料ID
     * @param basicPrice 变更的价格
     */
    protected void updateBasicFormula(Integer basicId, Float basicPrice) {
        int count = 0;
        // 更新基础产品配方表的
        List<FormulaBo> formulaBos = basicFormulaMapper.findFormulaBoByBasicId(basicId);
        for (FormulaBo bo : formulaBos) {
            float countPrice = bo.getCount() * basicPrice;
            count += basicFormulaMapper.updateFormulaPrice(bo.getId(), countPrice);
        }
        checkResult(count, formulaBos.size());
    }

    /**
     * 更新中级产品价格
     * @param middleIds
     */
    protected void updateMiddle(Set<Integer> middleIds) {
        int count = 0;
        for (Integer id : middleIds) {
            float price = 0f;
            List<FormulaVo> formulaVos = materialFormulaMapper.findFormulaVoByMiddleId(id);
            for (FormulaVo vo : formulaVos) {
                price += vo.getPrice();
            }
            count += middleMapper.updateMiddlePrice(id, price);
        }
        checkResult(count, middleIds.size());
    }

}
