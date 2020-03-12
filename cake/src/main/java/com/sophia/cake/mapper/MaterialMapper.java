package com.sophia.cake.mapper;

import com.sophia.cake.entity.Material;
import java.util.List;

/**
 * @author lizhe
 * @date 2020/3/11 13:09
 **/
public interface MaterialMapper {

    /**
     * 查找所有的原材料
     * @return 结果
     */
    List<Material> findAll();

    /**
     * 添加原材料
     * @param material 原材料
     * @return 结果
     */
    int add(Material material);

    /**
     * 更新内容
     * @param material 原材料
     * @return 结果
     */
    int update(Material material);

    /**
     * 删除内容
     * @param id ID
     * @return 结果
     */
    int delete(Integer id);
}
