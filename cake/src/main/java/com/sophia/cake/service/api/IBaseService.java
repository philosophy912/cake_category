package com.sophia.cake.service.api;

import com.philosophy.base.common.Pair;
import com.philosophy.base.entity.EnvData;
import com.sophia.cake.entity.bo.EntityBo;
import com.sophia.cake.entity.bo.NameBo;

import java.util.List;

/**
 * @author lizhe
 * @date 2020/4/1 10:04
 **/
public interface IBaseService<T> {

    Pair<List<T>, EnvData> query(EntityBo entityBo);

    Pair<List<T>, EnvData> queryName(NameBo nameBo);

    void add(T t);

    void delete(T t);

    void update(T t);


}
