package com.sophia.cake.service.impl;

import com.sophia.cake.entity.po.Material;
import com.sophia.cake.entity.vo.FormulaVo;
import com.sophia.cake.entity.vo.MVo;
import com.sophia.cake.service.api.BaseService;
import com.sophia.cake.service.api.IMaterialService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author lizhe
 * @date 2020-03-25 22:49
 */
@Service
@Slf4j
public class MaterialService extends BaseService implements IMaterialService {
    @Override
    public List<MVo> queryMaterial() {
        return materialMapper.findMVos();
    }

    @Override
    public List<Material> query() {
        return materialMapper.findMaterials();
    }

    @Override
    public List<Material> queryName(String name) {
        return materialMapper.findMaterialsByName("%" + name + "%");
    }

    @Override
    @Transactional
    public void add(Material material) {
        utils.update(material);
        checkResult(materialMapper.addMaterial(material), 1);
    }

    @Override
    @Transactional
    public void delete(Material material) {
        checkResult(materialMapper.deleteMaterialById(material.getId()), 1);
    }

    @Override
    @Transactional
    public void update(Material material) {
        checkResult(materialMapper.updateMaterial(material), 1);
        /**
         * 更新t_material_formula表中的价格
         */
        int materialId = material.getId();

    }
}
