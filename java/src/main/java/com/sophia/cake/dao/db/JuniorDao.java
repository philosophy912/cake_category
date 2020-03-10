package com.sophia.cake.dao.db;

import com.sophia.cake.entity.db.Junior;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author lizhe
 * @date 2020/3/10 18:00
 **/
public interface JuniorDao extends JpaRepository<Junior, Integer> {
}
