package com.sophia.cake.service;

import com.sophia.cake.entity.Material;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @author lizhe
 * @date 2020-03-08 22:13
 */
@Service
@Slf4j
public class MaterialService  extends BaseService<Material>{


    @Override
    public List<Material> query() {
        return materialDao.query();
    }

    @Override
    public boolean update(Material material) {
        try{
            materialDao.update(material);
            return true;
        }catch (IOException e){
            log.error(e.getMessage());
            return false;
        }

    }

    @Override
    public boolean add(Material material) {
        try{
            materialDao.add(material);
            return true;
        }catch (IOException e){
            log.error(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(Material material) {
        try {
            materialDao.remove(material.getId());
            return true;
        } catch (IOException e) {
            log.error(e.getMessage());
            return false;
        }
    }
}
