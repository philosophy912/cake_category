package com.sophia.cake.service.entity;

import com.sophia.cake.dao.BasicDao;
import com.sophia.cake.entity.Basic;
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
public class BasicService extends BaseService<Basic> {

    private BasicDao basicDao;

    @Autowired
    public void setBasicDao(BasicDao basicDao) {
        this.basicDao = basicDao;
    }


    @Override
    public List<Basic> query() {
        return basicDao.findAll();
    }

    @Override
    public Basic update(Basic basic) {
        Basic search = new Basic();
        search.setId(basic.getId());
        Example<Basic> example = Example.of(search);
        Optional<Basic> optional = basicDao.findOne(example);
        // 能够在数据库中查找到数据
        if (optional.isPresent()) {
            Basic product = optional.get();
            updateUtil.copy(basic, product);
            return basicDao.saveAndFlush(product);
        }
        return null;
    }

    public Basic add(Basic basic) {
        log.info("Basic = {}", basic);
        basic.update();
        Basic product = basicDao.saveAndFlush(basic);
        log.info("product = {}", product);
        return product;
    }

    @Override
    public boolean delete(Basic basic) {
        return false;
    }
}
