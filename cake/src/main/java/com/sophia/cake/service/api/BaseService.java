package com.sophia.cake.service.api;

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

    protected void updateBasicVo(BasicVo vo) {
        float price = 0f;
        Set<FormulaVo> formulaVos = vo.getFormulas();
        for (FormulaVo formulaVo : formulaVos) {
            // 只有原材料的ID，所以要获取原材料的价格
            Material material = materialMapper.findMaterialById(formulaVo.getId());
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
            // 原材料
            if (formulaVo.getType().equalsIgnoreCase(FormulaType.MATERIAL.getValue())) {
                Material material = materialMapper.findMaterialById(formulaVo.getId());
                formulaPrice = formulaVo.getCount() * material.getPricePerUnit();
            }else{
                //基础产品
                BasicVo basicVo = basicMapper.findBasicVoById(formulaVo.getId());
                formulaPrice = formulaVo.getCount() * basicVo.getPrice();
            }
            formulaVo.setPrice(formulaPrice);
            price += formulaPrice;
        }
        vo.setPrice(price);
    }
}
