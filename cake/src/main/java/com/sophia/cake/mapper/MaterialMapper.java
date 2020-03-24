package com.sophia.cake.mapper;

import com.sophia.cake.entity.po.Material;

/**
 * @author lizhe
 * @date 2020-03-24 22:36
 */
public interface MaterialMapper {
    // 查找所有的原材料
    int findMaterials();
    // 根据ID找Material
    int findMaterialById(Integer id);
    // 添加一个Material对象
    int addMaterial(Material material);
    // 更新material
    int updateMaterial(Material material);
    // 删除Material
    int deleteMaterialByID(Integer id);

}
