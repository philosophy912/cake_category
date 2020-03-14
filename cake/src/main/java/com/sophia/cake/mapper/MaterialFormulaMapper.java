package com.sophia.cake.mapper;

import com.sophia.cake.entity.BasicFormula;
import com.sophia.cake.entity.MaterialFormula;

import java.util.List;

/**
 * @author lizhe
 * @date 2020-03-14 21:38
 */
public interface MaterialFormulaMapper {

    List<MaterialFormula> findById(Integer id);
}
