package com.sophia.cake.service.db;

import com.sophia.cake.entity.db.Junior;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lizhe
 * @date 2020-03-10 21:52
 */
@Service
@Slf4j
public class JuniorService extends AbstractService<Junior> {


    @Override
    public List<Junior> query() {
        return juniorDao.findAll();
    }

    @Override
    public boolean update(Junior junior) {
        return juniorDao.update(junior) == 1;
    }

    @Override
    public boolean add(Junior junior) {
        // 保证不会存在ID值，让数据自增长
        if(junior.getId() == null){
            junior.setId(null);
        }
        Junior saved = juniorDao.saveAndFlush(junior);
        return saved.getId() != null;
    }

    @Override
    public boolean delete(Junior junior) {
        boolean result = false;
        try {
            juniorDao.deleteById(junior.getId());
            result = true;
        }catch (IllegalArgumentException e){
            log.warn(e.getMessage());
        }
        return result;
    }
}
