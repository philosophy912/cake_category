package com.sophia.cake.service;

import com.sophia.cake.entity.Middle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @author lizhe
 * @date 2020-03-09 21:26
 */
@Service
@Slf4j
public class MiddleService extends BaseService<Middle> {


    @Override
    public List<Middle> query() {
        return middleDao.query();
    }

    @Override
    public boolean update(Middle middle) {
        try {
            middleDao.update(middle);
            return true;
        } catch (IOException e) {
            log.error(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean add(Middle middle) {
        try {
            middleDao.add(middle);
            return true;
        } catch (IOException e) {
            log.error(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(Middle middle) {
        try {
            middleDao.remove(middle.getId());
            return true;
        } catch (IOException e) {
            log.error(e.getMessage());
            return false;
        }
    }
}
