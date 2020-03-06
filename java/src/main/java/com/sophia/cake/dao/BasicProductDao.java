package com.sophia.cake.dao;

import com.sophia.cake.entity.BasicProduct;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author lizhe
 * @Description description
 * @date 2020/3/6 12:45
 **/
public interface BasicProductDao extends JpaRepository<BasicProduct, Integer> {
}
