package com.sophia.cake.service.impl;

import com.philosophy.base.common.Pair;
import com.philosophy.base.entity.EnvData;
import com.sophia.cake.entity.bo.EntityBo;
import com.sophia.cake.entity.bo.NameBo;
import com.sophia.cake.entity.po.Material;
import com.sophia.cake.entity.vo.BasicVo;
import com.sophia.cake.entity.vo.MVo;
import com.sophia.cake.service.api.BaseService;
import com.sophia.cake.service.api.IMaterialService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

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
    public Pair<List<Material>, EnvData> query(EntityBo entityBo) {
        int index = entityBo.getEnvData().getPageNo() - 1;
        int pageSize = entityBo.getEnvData().getPageSize();
        log.debug("index = [{}], pageSize = [{}]", index, pageSize);
        int totalRows = materialMapper.findMaterialCount();
        int totalPages = utils.getTotalPages(totalRows, pageSize);
        log.debug("totalRows = [{}], totalPages = [{}]", totalRows, totalPages);
        List<Material> materials = materialMapper.findPageMaterials(index, pageSize);
        return new Pair<>(materials, getEnvData(index, pageSize, totalRows, totalPages));
    }


    @Override
    public Pair<List<Material>, EnvData> queryName(NameBo nameBo) {
        String name = "%" + nameBo.getName() + "%";
        int index = nameBo.getEnvData().getPageNo() - 1;
        int pageSize = nameBo.getEnvData().getPageSize();
        log.debug("index = [{}], pageSize = [{}]", index, pageSize);
        int totalRows = materialMapper.findMaterialByNameCount(name);
        int totalPages = utils.getTotalPages(totalRows, pageSize);
        log.debug("totalRows = [{}], totalPages = [{}]", totalRows, totalPages);
        List<Material> materials = materialMapper.findPageMaterialsByName(name, index, pageSize);
        return new Pair<>(materials, getEnvData(index, pageSize, totalRows, totalPages));
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
        // 更新原材料价格
        Float pricePerUnit = updateMaterial(material);
        // 更新原材料配方价格
        Pair<Set<Integer>, Set<Integer>> pair = updateMaterialFormula(material.getId(), pricePerUnit);
        // 更新基础材料价格
        Set<Integer> middleIds = updateBasic(pair);
        // 更新中级材料价格
        updateMiddle(middleIds);
    }
}
