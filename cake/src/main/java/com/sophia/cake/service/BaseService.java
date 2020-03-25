package com.sophia.cake.service;

import com.sophia.cake.mapper.BasicMapper;
import com.sophia.cake.mapper.FormulaMapper;
import com.sophia.cake.mapper.MaterialMapper;
import com.sophia.cake.mapper.MiddleMapper;

import javax.annotation.Resource;

/**
 * @author lizhe
 * @date 2020-03-25 22:47
 */
public abstract class BaseService {
    @Resource
    protected FormulaMapper formulaMapper;
    @Resource
    protected BasicMapper basicMapper;
    @Resource
    protected MaterialMapper materialMapper;
    @Resource
    protected MiddleMapper middleMapper;
}
