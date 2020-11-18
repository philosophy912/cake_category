package com.sophia.cake.dao;

import com.sophia.cake.entity.po.Material;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMaterialDao extends JpaRepository<Material, Integer> {
}
