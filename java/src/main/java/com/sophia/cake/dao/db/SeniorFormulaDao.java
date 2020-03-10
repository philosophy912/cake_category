package com.sophia.cake.dao.db;

import com.sophia.cake.entity.db.SeniorFormula;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author lizhe
 * @date 2020/3/10 18:01
 **/
public interface SeniorFormulaDao extends JpaRepository<SeniorFormula, Integer> {
}
