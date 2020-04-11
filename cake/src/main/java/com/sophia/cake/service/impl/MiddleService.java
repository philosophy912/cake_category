package com.sophia.cake.service.impl;

import com.philosophy.base.common.Pair;
import com.philosophy.base.entity.EnvData;
import com.sophia.cake.entity.bo.EntityBo;
import com.sophia.cake.entity.bo.MiddleBo;
import com.sophia.cake.entity.FormulaType;
import com.sophia.cake.entity.bo.NameBo;
import com.sophia.cake.entity.po.Material;
import com.sophia.cake.entity.vo.BasicVo;
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


    @Override
    public Pair<List<MiddleVo>, EnvData> query(EntityBo entityBo) {
        int pageNo = entityBo.getEnvData().getPageNo() - 1;
        int pageSize = entityBo.getEnvData().getPageSize();
        int index = pageNo * pageSize;
        int totalRows = middleMapper.findMiddleCount();
        int totalPages = utils.getTotalPages(totalRows, pageSize);
        List<MiddleVo> middleVos = new ArrayList<>();
        middleMapper.findPageMiddleBos(index, pageSize).forEach(middleBo -> {
            MiddleVo vo = utils.convert(middleBo);
            middleVos.add(vo);
        });
        return new Pair<>(middleVos, getEnvData(pageNo, pageSize, totalRows, totalPages));
    }


    @Override
    public Pair<List<MiddleVo>, EnvData> queryName(NameBo nameBo) {
        String name = "%" + nameBo.getName() + "%";
        int pageNo = nameBo.getEnvData().getPageNo() - 1;
        int pageSize = nameBo.getEnvData().getPageSize();
        int index = pageNo * pageSize;
        int totalRows = middleMapper.findBasicByNameCount(name);
        int totalPages = utils.getTotalPages(totalRows, pageSize);
        List<MiddleVo> middleVos = new ArrayList<>();
        middleMapper.findPageMiddleBosByName(name, index, pageSize).forEach(middleBo -> {
            MiddleVo vo = utils.convert(middleBo);
            middleVos.add(vo);
        });
        return new Pair<>(middleVos, getEnvData(pageNo, pageSize, totalRows, totalPages));
    }


    @Transactional
    @Override
    public void add(MiddleVo middleVo) {
        updateMiddleVo(middleVo);
        checkResult(1, middleMapper.addMiddleVo(middleVo));
        Set<FormulaVo> formulas = middleVo.getFormulas();
        for (FormulaVo vo : formulas) {
            FormulaType type = FormulaType.fromValue(vo.getType());
            if (type == FormulaType.MATERIAL) {
                checkResult(1, materialFormulaMapper.addMiddleFormulaVo(vo));
            } else if (type == FormulaType.BASIC) {
                checkResult(1, basicFormulaMapper.addFormulaVo(vo));
            }
        }
    }

    @Transactional
    @Override
    public void delete(MiddleVo middleVo) {
        MiddleBo bo = middleMapper.findMiddleBoById(middleVo.getId());
        if (bo == null) {
            throw new RuntimeException("not found Middle where id = " + middleVo.getId());
        } else {
            MiddleVo mvo = utils.convert(bo);
            int middleId = mvo.getId();
            log.debug("try to delete middle id = {}", middleId);
            Set<FormulaVo> formulas = mvo.getFormulas();
            for (FormulaVo vo : formulas) {
                log.debug("vo = {}", vo);
                FormulaType type = FormulaType.fromValue(vo.getType());
                int id = vo.getFid();
                if (type == FormulaType.MATERIAL) {
                    checkResult(1, materialFormulaMapper.deleteMiddleFormulaVoById(id));
                } else if (type == FormulaType.BASIC) {
                    checkResult(1, basicFormulaMapper.deleteFormulaVoById(id));
                }
            }
            checkResult(1, middleMapper.deleteMiddleById(middleId));
        }

    }

    @Transactional
    @Override
    public void update(MiddleVo middleVo) {
        float totalPrice = 0f;
        int id = middleVo.getId();
        log.debug("middleId = {}", id);
        MiddleBo middleBo = middleMapper.findMiddleBoById(id);
        if (middleBo != null) {
            MiddleVo vo = utils.convert(middleBo);
            Set<FormulaVo> formulaVos = vo.getFormulas();
            for (FormulaVo formulaVo : formulaVos) {
                FormulaType type = FormulaType.fromValue(formulaVo.getType());
                int fid = formulaVo.getId();
                log.debug("fid = {}", fid);
                if (type == FormulaType.MATERIAL) {
                    Material material = materialMapper.findMaterialById(fid);
                    float price = formulaVo.getCount() * material.getPricePerUnit();
                    formulaVo.setPrice(price);
                    totalPrice += price;
                    checkResult(1, materialFormulaMapper.updateFormulaVo(formulaVo));
                } else if (type == FormulaType.BASIC) {
                    BasicVo basicVo = basicMapper.findBasicVoById(fid);
                    float price = formulaVo.getCount() * basicVo.getPrice();
                    formulaVo.setPrice(price);
                    totalPrice += price;
                    checkResult(1, basicFormulaMapper.updateFormulaVo(formulaVo));
                }
            }
            vo.setPrice(totalPrice);
            checkResult(1, middleMapper.updateMiddleVo(vo));
        } else {
            throw new RuntimeException("middle[" + id + "] not found ");
        }
    }

}
