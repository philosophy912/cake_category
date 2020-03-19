package com.sophia.cake.service.impl;

import com.sophia.cake.entity.Material;
import com.sophia.cake.mapper.MaterialMapper;
import com.sophia.cake.service.api.IService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lizhe
 * @date 2020/3/12 15:47
 **/
@Service
public class MaterialService implements IService<Material> {

    @Resource
    private MaterialMapper materialMapper;


    @Override
    public List<Material> findAll() {
        return materialMapper.findAll();
    }

    @Override
    public boolean add(Material material) {
        return materialMapper.add(material) == 1;
    }

    @Override
    public boolean delete(Integer id) {
        return materialMapper.delete(id) == 1;
    }

    @Override
    public boolean update(Material material) {
        return materialMapper.update(material) == 1;
    }

}
