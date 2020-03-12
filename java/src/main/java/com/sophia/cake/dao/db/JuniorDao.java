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

}
