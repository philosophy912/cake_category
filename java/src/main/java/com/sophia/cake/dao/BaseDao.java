package com.sophia.cake.dao;

import com.philosophy.txt.util.TxtUtils;
import com.sophia.cake.config.BaseConfigure;
import com.sophia.cake.utils.DbUtils;
import com.sophia.cake.utils.UpdateUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

/**
 * @author lizhe
 * @date 2020-03-08 22:17
 */
public abstract class BaseDao {
    @Autowired
    protected BaseConfigure baseConfigure;
    @Autowired
    protected DbUtils dbUtils;
    @Autowired
    protected TxtUtils txtUtils;
    @Autowired
    protected UpdateUtil updateUtil;

    protected String getUUID() {
        return UUID.randomUUID().toString();
    }
}
