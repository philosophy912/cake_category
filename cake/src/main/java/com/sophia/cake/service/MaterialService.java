package com.sophia.cake.service;

import com.sophia.cake.entity.po.Material;
import com.sophia.cake.entity.vo.BasicVo;
import com.sophia.cake.entity.vo.MVo;
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
public class MaterialService extends BaseService {

    public List<MVo> queryMaterial() {
        return materialMapper.findMVos();
    }

    public List<Material> query() {
        return materialMapper.findMaterials();
    }

    public List<Material> queryName(String name) {
        return materialMapper.findMaterialsByName("%" + name + "%");
    }

    public boolean add(Material material) {
        return materialMapper.addMaterial(material) == 1;
    }

    public boolean delete(Integer id) {
        // todo 先要判断是否其他地方引用了这个原材料，如果有则不会删除成功
        return materialMapper.deleteMaterialByID(id) == 1;
    }

    public boolean update(Material material) {
        return materialMapper.updateMaterial(material) == 1;
    }
}
