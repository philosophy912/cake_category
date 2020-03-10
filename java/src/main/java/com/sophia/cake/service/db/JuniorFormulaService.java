package com.sophia.cake.service.db;

import com.sophia.cake.entity.db.JuniorFormula;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lizhe
 * @date 2020-03-10 22:34
 */
@Service
@Slf4j
public class JuniorFormulaService extends AbstractService<JuniorFormula> {


    @Override
    public List<JuniorFormula> query() {
        return juniorFormulaDao.findAll();
    }

    @Override
    public boolean update(JuniorFormula juniorFormula) {
        return juniorFormulaDao.update(juniorFormula) == 1;
    }

    @Override
    public boolean add(JuniorFormula juniorFormula) {
        if (juniorFormula.getId() == null) {
            juniorFormula.setId(null);
        }
        JuniorFormula saved = juniorFormulaDao.saveAndFlush(juniorFormula);
        return saved.getId() != null;
    }

    @Override
    public boolean delete(JuniorFormula juniorFormula) {
        boolean result = false;
        try {
            juniorFormulaDao.deleteById(juniorFormula.getId());
            result = true;
        } catch (IllegalArgumentException e) {
            log.warn(e.getMessage());
        }
        return result;
    }
}
