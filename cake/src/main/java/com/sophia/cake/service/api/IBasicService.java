package com.sophia.cake.service.api;

import com.sophia.cake.entity.vo.BVo;
import com.sophia.cake.entity.vo.BasicVo;

import java.util.List;

/**
 * @author lizhe
 * @date 2020/4/1 10:02
 **/
public interface IBasicService extends IBaseService<BasicVo> {

    List<BVo> queryBasic();

}
