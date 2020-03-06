package com.sophia.cake.service.entity;

import com.sophia.cake.dao.MiddleDao;
import com.sophia.cake.entity.Middle;
import com.sophia.cake.service.base.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author lizhe
 * @date 2020-02-25 11:32
 */
@Service
@Slf4j
public class MiddleService extends BaseService<Middle> {
    private MiddleDao middleDao;

    @Autowired
    public void setMiddleDao(MiddleDao middleDao) {
        this.middleDao = middleDao;
    }


    @Override
    public List<Middle> query() {
        return middleDao.findAll();
    }

    @Override
    public Middle update(Middle middle) {
        Middle search = new Middle();
        search.setId(middle.getId());
        Example<Middle> example = Example.of(search);
        Optional<Middle> optional = middleDao.findOne(example);
        // 能够在数据库中查找到数据
        if (optional.isPresent()) {
            Middle product = optional.get();
            updateUtil.copy(middle, product);
            return middleDao.saveAndFlush(product);
        }
        return null;
    }

    public Middle add(Middle middle) {
        log.info("Middle = {}", middle);
        middle.update();
        Middle product = middleDao.saveAndFlush(middle);
        log.info("product = {}", product);
        return product;
    }

    @Override
    public boolean delete(Middle middle) {
        try {
            middleDao.delete(middle);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
