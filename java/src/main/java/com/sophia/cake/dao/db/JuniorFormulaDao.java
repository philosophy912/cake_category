package com.sophia.cake.dao.db;

import com.sophia.cake.entity.db.Junior;
import com.sophia.cake.entity.db.JuniorFormula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

/**
 * @author lizhe
 * @date 2020/3/10 18:01
 **/
public interface JuniorFormulaDao extends JpaRepository<JuniorFormula, Integer> {

    @Modifying
    @Transactional
    @Query("update JuniorFormula j set " +
            "j.count = CASE WHEN: #{#juniorFormula.count} IS NULL THEN j.count " +
            "ELSE: #{#juniorFormula.count} END ," +
            "j.totalPrice = CASE WHEN: #{#juniorFormula.totalPrice} IS NULL THEN j.totalPrice " +
            "ELSE: #{#juniorFormula.totalPrice} END ," +
            "j.junior = CASE WHEN: #{#juniorFormula.junior} IS NULL THEN j.junior " +
            "ELSE: #{#juniorFormula.junior} END " +
            "where j.id = :#{#juniorFormula.id}")
    int update(@Param("juniorFormula") JuniorFormula juniorFormula);
}
