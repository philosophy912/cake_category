package com.sophia.cake.mapper;

import com.sophia.cake.entity.BasicFormula;

import java.util.List;

/**
 * @author lizhe
 * @date 2020-03-14 21:38
 */
public interface BasicFormulaMapper {

    List<BasicFormula> findById(Integer id);
}
