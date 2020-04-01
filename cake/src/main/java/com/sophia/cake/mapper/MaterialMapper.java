package com.sophia.cake.mapper;

import com.sophia.cake.entity.po.Material;
import com.sophia.cake.entity.vo.MVo;

import java.util.List;
import java.util.Set;

/**
 * @author lizhe
 * @date 2020-03-24 22:36
 */
public interface MaterialMapper {
    // 查找所有的原材料
    List<Material> findMaterials();

    // 根据ID查找原材料
    Material findMaterialById(Integer id);

    // 根据名字查找原材料
    Material findMaterialByName(String name);

    // 根据名字找原材料集合
    List<Material> findMaterialsByName(String name);

    // 查找所有的原材料的MVo
    List<MVo> findMVos();

    // 添加一个原材料
    int addMaterial(Material material);

    // 添加多个原材料
    int addMaterials(Set<Material> materials);

    // 更新原材料
    int updateMaterial(Material material);

    // 删除原材料
    int deleteMaterialById(Integer id);


}
