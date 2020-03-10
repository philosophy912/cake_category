package com.sophia.cake.dao.db;

import com.sophia.cake.entity.db.Junior;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

/**
 * @author lizhe
 * @date 2020/3/10 18:00
 **/
public interface JuniorDao extends JpaRepository<Junior, Integer> {

    @Modifying
    @Transactional
    @Query("update Junior set " +
            "name = CASE WHEN: #{#junior.name} IS NULL THEN name " +
            "ELSE: #{#junior.name} END ," +
            "capacity = CASE WHEN: #{#junior.capacity} IS NULL THEN capacity " +
            "ELSE: #{#junior.capacity} END ," +
            "capacityType = CASE WHEN: #{#junior.capacityType} IS NULL THEN capacityType " +
            "ELSE: #{#junior.capacityType} END ," +
            "price = CASE WHEN: #{#junior.price} IS NULL THEN price " +
            "ELSE: #{#junior.price} END ," +
            "pricePerCapacity = CASE WHEN: #{#junior.pricePerCapacity} IS NULL THEN pricePerCapacity " +
            "ELSE: #{#junior.pricePerCapacity} END " +
            "where id = :#{#junior.id}")
    int update(@Param("junior") Junior junior);

}
