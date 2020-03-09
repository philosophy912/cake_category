package com.sophia.cake.dao;

import com.philosophy.txt.util.TxtUtils;
import com.sophia.cake.config.BaseConfigure;
import com.sophia.cake.utils.DbUtils;
import com.sophia.cake.utils.UpdateUtil;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;

import java.nio.file.Path;
import java.util.List;
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

    protected Path path;
    protected String charset;

    @SneakyThrows
    protected List<String> readFromFile() {
        return txtUtils.read(path, charset, true);
    }

    protected String getUUID() {
        return UUID.randomUUID().toString();
    }
}
