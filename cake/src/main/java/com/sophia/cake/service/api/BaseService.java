package com.sophia.cake.service.api;

import com.philosophy.base.common.Pair;
import com.philosophy.base.entity.EnvData;
import com.sophia.cake.entity.bo.EntityBo;
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
            log.debug("formulaBo = {}", formulaBo);
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
            log.debug("id = [{}] and price = [{}]", id, price);
            checkResult(1, materialFormulaMapper.updateFormulaPrice(id, price));
        }
        return new Pair<>(basicIds, middleIds);
    }

    /**
     * 更新Basic
     *
     * @param pair 包含了影响的BasicId和MiddleId
     * @return 受到影响的MiddleId
     */
    protected Set<Integer> updateBasic(Pair<Set<Integer>, Set<Integer>> pair) {
        Set<Integer> basicIds = pair.getFirst();
        Set<Integer> middleIds = pair.getSecond();
        for (Integer id : basicIds) {
            log.debug("basic Id = {}", id);
            float price = 0f;
            List<FormulaVo> formulaVos = materialFormulaMapper.findFormulaVoByBasicId(id);
            for (FormulaVo vo : formulaVos) {
                log.debug("FormulaVo is {}", vo);
                price += vo.getPrice();
            }
            log.debug("id is [{}] and price is [{}]", id, price);
            checkResult(1, basicMapper.updateBasicPrice(id, price));
            updateBasicFormula(id, price);
            // 根据中级产品查询到影响的Middle产品
            List<FormulaBo> bos = basicFormulaMapper.findFormulaBoByBasicId(id);
            for (FormulaBo bo : bos) {
                log.debug("FormulaBo is {}", bo);
                middleIds.add(bo.getMiddle_id());
            }
        }
        return middleIds;
    }

    /**
     * 更新基础材料配方表
     *
     * @param basicId    基础材料ID
     * @param basicPrice 变更的价格
     */
    protected void updateBasicFormula(Integer basicId, Float basicPrice) {
        // 更新基础产品配方表的
        basicFormulaMapper.findFormulaBoByBasicId(basicId).forEach(formulaBo -> {
            log.debug("formulaBo = {}", formulaBo);
            float countPrice = formulaBo.getCount() * basicPrice;
            int id = formulaBo.getId();
            checkResult(1, basicFormulaMapper.updateFormulaPrice(id, countPrice));
        });
    }

    /**
     * 更新中级产品价格
     *
     * @param middleIds 中级产品
     */
    protected void updateMiddle(Set<Integer> middleIds) {
        for (Integer id : middleIds) {
            log.debug("middleId = {}", id);
            float price = 0f;
            List<FormulaVo> formulaVos = materialFormulaMapper.findFormulaVoByMiddleId(id);
            formulaVos.addAll(basicFormulaMapper.findFormulaVoById(id));
            for (FormulaVo vo : formulaVos) {
                log.debug("FormulaVo is {}", vo);
                price += vo.getPrice();
            }
            log.debug("id = [{}] and price is [{}]", id, price);
            checkResult(1, middleMapper.updateMiddlePrice(id, price));
        }
    }

    protected void updateBasicVo(BasicVo vo) {
        float price = 0f;
        Set<FormulaVo> formulaVos = vo.getFormulas();
        for (FormulaVo formulaVo : formulaVos) {
            int id = formulaVo.getId();
            log.debug("material id = {}", id);
            // 只有原材料的ID，所以要获取原材料的价格
            Material material = materialMapper.findMaterialById(id);
            // 计算出来总价
            float formulaPrice = formulaVo.getCount() * material.getPricePerUnit();
            // 将总价设置到其中
            formulaVo.setPrice(formulaPrice);
            price += formulaPrice;
        }
        vo.setPrice(price);
    }

    protected void updateMiddleVo(MiddleVo vo) {
        float price = 0f;
        Set<FormulaVo> formulaVos = vo.getFormulas();
        for (FormulaVo formulaVo : formulaVos) {
            float formulaPrice;
            int id = formulaVo.getId();
            // 原材料
            if (formulaVo.getType().equalsIgnoreCase(FormulaType.MATERIAL.getValue())) {
                Material material = materialMapper.findMaterialById(id);
                formulaPrice = formulaVo.getCount() * material.getPricePerUnit();
            } else {
                //基础产品
                BasicVo basicVo = basicMapper.findBasicVoById(id);
                formulaPrice = formulaVo.getCount() * basicVo.getPrice();
            }
            formulaVo.setPrice(formulaPrice);
            price += formulaPrice;
        }
        vo.setPrice(price);
    }


    protected EnvData getEnvData(int index, int pageSize, int totalRows, int totalPages) {
        EnvData envData = new EnvData();
        envData.setPageNo(index);
        envData.setPageNo(pageSize);
        envData.setTotalRows(totalRows);
        envData.setTotalPages(totalPages);
        return envData;
    }

    protected Pair<Integer, Integer> getPage(EntityBo entityBo) {
        return new Pair<>(entityBo.getEnvData().getPageNo() - 1, entityBo.getEnvData().getPageSize());
    }
}
