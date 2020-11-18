package com.sophia.cake.dao;

import com.sophia.cake.entity.po.BasicFormula;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBasicFormulaDao extends JpaRepository<BasicFormula, Integer> {
}
