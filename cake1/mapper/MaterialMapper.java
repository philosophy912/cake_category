package com.sophia.cake.mapper;

import com.sophia.cake.entity.po.Material;
import com.sophia.cake.entity.vo.MaterialVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

import static com.sophia.cake.constant.TABLE.MATERIAL;

/**
 * @author lizhe
 * @date 2020/3/11 13:09
 **/
public interface MaterialMapper {

    /**
     * 查找所有的原材料
     *
     * @return 结果
     */
    List<Material> findAll();

    List<MaterialVo> findMaterials();

    /**
     * 添加原材料
     *
     * @param material 原材料
     * @return 结果
     */
    int add(Material material);

    /**
     * 更新内容
     *
     * @param material 原材料
     * @return 结果
     */
    int update(Material material);

    /**
     * 删除内容
     *
     * @param id ID
     * @return 结果
     */
    int delete(Integer id);

    /**
     * 根据ID查找
     *
     * @param id ID
     * @return 结果
     */
    Material findById(Integer id);

    /**
     * 根据名字查
     *
     * @param name 名字
     * @return 结果
     */
    Material findByName(String name);


}
