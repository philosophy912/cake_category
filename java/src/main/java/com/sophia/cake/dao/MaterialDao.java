package com.sophia.cake.dao;

import com.sophia.cake.entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author lizhe
 * @date 2020-02-25 11:29
 */
public interface MaterialDao extends JpaRepository<Material, Integer> {
}