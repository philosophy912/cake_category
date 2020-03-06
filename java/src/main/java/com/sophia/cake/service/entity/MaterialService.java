package com.sophia.cake.service.entity;

import com.sophia.cake.dao.MaterialDao;
import com.sophia.cake.entity.Material;
import com.sophia.cake.service.base.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author lizhe
 * @date 2020-02-25 11:31
 */
@Service
@Slf4j
public class MaterialService extends BaseService<Material> {

    private MaterialDao materialDao;

    @Autowired
    public void setMaterialDao(MaterialDao materialDao) {
        this.materialDao = materialDao;
    }


    @Override
    public List<Material> query() {
        return materialDao.findAll();
    }

    @Override
    public Material update(Material material) {
        Material search = new Material();
        search.setId(material.getId());
        Example<Material> example = Example.of(search);
        Optional<Material> optional = materialDao.findOne(example);
        // 能够在数据库中查找到数据
        if (optional.isPresent()) {
            Material product = optional.get();
            updateUtil.copy(material, product);
            return materialDao.saveAndFlush(product);
        }
        return null;
    }

    public Material add(Material material) {
        log.info("material = {}", material);
        material.update();
        Material product = materialDao.saveAndFlush(material);
        log.info("product = {}", product);
        return product;
    }

    @Override
    public boolean delete(Material material) {
        try {
            materialDao.delete(material);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }


}
