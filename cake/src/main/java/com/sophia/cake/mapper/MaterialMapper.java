package com.sophia.cake.mapper;

import com.sophia.cake.entity.po.Material;
import com.sophia.cake.entity.vo.MVo;

import java.util.List;

/**
 * @author lizhe
 * @date 2020-04-05 22:10
 */
public interface MaterialMapper {
    // 添加一个原材料
    int addMaterial(Material material);
    // 删除原材料
    int deleteMaterialById(Integer id);
    // 更新原材料
    int updateMaterial(Material material);
    // 根据ID查找原材料
    Material findMaterialById(Integer id);
    // 查找所有的原材料的MVo
    List<MVo> findMVos();
    // 根据名字找原材料集合
    List<Material> findMaterials();
    // 根据名字找原材料集合
    List<Material> findMaterialsByName(String name);

}
