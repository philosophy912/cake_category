package com.sophia.cake.dao;

import com.sophia.cake.entity.MaterialProduct;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author lizhe
 * @Description description
 * @date 2020/3/6 12:46
 **/
public interface MaterialProductDao extends JpaRepository<MaterialProduct, Integer> {
}
