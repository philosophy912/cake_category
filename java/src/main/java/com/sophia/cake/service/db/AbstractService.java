package com.sophia.cake.service.db;

import com.sophia.cake.dao.db.JuniorDao;
import com.sophia.cake.dao.db.JuniorFormulaDao;
import com.sophia.cake.dao.db.SeniorDao;
import com.sophia.cake.dao.db.SeniorFormulaDao;
import com.sophia.cake.dao.db.SuperiorDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author lizhe
 * @date 2020-03-10 21:52
 */
public abstract class AbstractService<T> {
    @Autowired
    protected JuniorDao juniorDao;
    @Autowired
    protected JuniorFormulaDao juniorFormulaDao;
    @Autowired
    protected SeniorDao seniorDao;
    @Autowired
    protected SeniorFormulaDao seniorFormulaDao;
    @Autowired
    protected SuperiorDao superiorDao;

    public abstract List<T> query();

    public abstract boolean update(T t);

    public abstract boolean add(T t);

    public abstract boolean delete(T t);
}
