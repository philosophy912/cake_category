package com.sophia.cake.service.api;

import com.sophia.cake.entity.po.Material;
import com.sophia.cake.entity.vo.MVo;

import java.util.List;

/**
 * @author lizhe
 * @date 2020/4/1 10:06
 **/
public interface IMaterialService extends IBaseService<Material> {

    List<MVo> queryMaterial();
}
