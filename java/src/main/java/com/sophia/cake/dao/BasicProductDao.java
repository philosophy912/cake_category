package com.sophia.cake.dao;

import com.sophia.cake.entity.Basic;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author lizhe
 * @date 2020-02-25 11:29
 */
public interface BasicProductDao extends JpaRepository<Basic, Integer> {
}
