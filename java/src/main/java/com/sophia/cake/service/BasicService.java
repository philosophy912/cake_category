package com.sophia.cake.service;

import com.sophia.cake.entity.Basic;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @author lizhe
 * @date 2020-03-09 21:21
 */
@Service
@Slf4j
public class BasicService extends BaseService<Basic> {


    @Override
    public List<Basic> query() {
        return basicDao.query();
    }

    @Override
    public boolean update(Basic basic) {
        try{
            basicDao.update(basic);
            return true;
        }catch (IOException e){
            log.error(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean add(Basic basic) {
        try{
            basicDao.add(basic);
            return true;
        }catch (IOException e){
            log.error(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(Basic basic) {
        try {
            basicDao.remove(basic.getId());
            return true;
        } catch (IOException e) {
            log.error(e.getMessage());
            return false;
        }
    }
}
