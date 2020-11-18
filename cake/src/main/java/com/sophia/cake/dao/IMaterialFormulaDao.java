package com.sophia.cake.dao;

import com.sophia.cake.entity.po.MaterialFormula;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMaterialFormulaDao extends JpaRepository<MaterialFormula, Integer> {
}
