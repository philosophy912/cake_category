package com.sophia.cake.service.vo;

import com.sophia.cake.entity.po.Basic;
import com.sophia.cake.entity.po.MaterialFormula;
import com.sophia.cake.entity.vo.BasicVo;
import com.sophia.cake.entity.vo.MaterialFormulaVo;
import com.sophia.cake.mapper.BasicMapper;
import com.sophia.cake.mapper.MaterialMapper;
import com.sophia.cake.service.api.IService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author lizhe
 * @date 2020-03-21 19:38
 */
@Service
public class BasicVoService implements IService<BasicVo> {

    @Resource
    private BasicMapper basicMapper;
    @Resource
    private MaterialMapper materialMapper;


    @Override
    public List<BasicVo> findAll() {
        return null;
    }

    @Override
    public boolean add(BasicVo basicVo) {
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public boolean update(BasicVo basicVo) {
        return false;
    }
}
