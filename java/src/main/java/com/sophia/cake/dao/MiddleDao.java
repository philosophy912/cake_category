package com.sophia.cake.dao;

import com.philosophy.base.util.ParseUtils;
import com.sophia.cake.entity.BasicProduct;
import com.sophia.cake.entity.MaterialProduct;
import com.sophia.cake.entity.Middle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static com.sophia.cake.api.IEntity.COMMA;
import static com.sophia.cake.api.IEntity.SEMICOLON;

/**
 * @author lizhe
 * @date 2020-03-08 22:13
 */
@Component
@Slf4j
public class MiddleDao extends BaseDao {


    private MaterialProductDao materialProductDao;
    private BasicProductDao basicProductDao;

    @Autowired
    public void setMaterialProductDao(MaterialProductDao materialProductDao) {
        this.materialProductDao = materialProductDao;
    }

    @Autowired
    public void setBasicProductDao(BasicProductDao basicProductDao) {
        this.basicProductDao = basicProductDao;
    }

    private void setParam() {
        if (null == path) {
            path = dbUtils.getPaths(baseConfigure.getMiddle());
        }
        if (null == charset) {
            charset = baseConfigure.getCharset();
        }
    }


    private Middle getMiddle(String content) {
        String[] values = content.split(COMMA);
        Middle middle = new Middle();
        // sb.append(id).append(COMMA).append(name).append(COMMA).append(totalPrice).append(COMMA);
        //  materialProducts.forEach(materialProduct -> sb.append(materialProduct.getId()).append(SEMICOLON));
        middle.setId(values[0]);
        middle.setName(values[1]);
        middle.setTotalPrice(Float.parseFloat(values[2]));
        String materialProductsIds = values[values.length - 1];
        String basicProductsIds = values[values.length - 2];
        String[] materialIds = materialProductsIds.split(SEMICOLON);
        log.debug("materialIds = {}", Arrays.toString(materialIds));
        String[] basicIds = basicProductsIds.split(SEMICOLON);
        log.debug("basicIds = {}", Arrays.toString(basicIds));
        Set<MaterialProduct> materialSet = new HashSet<>();
        Set<BasicProduct> basicSet = new HashSet<>();
        List<MaterialProduct> materialProducts = materialProductDao.query();
        List<BasicProduct> basicProducts = basicProductDao.query();
        materialProducts.forEach(materialProduct -> {
            log.debug("materialProduct id = {}", materialProduct.getId());
            for (String id : materialIds) {
                log.debug("id = {}", id);
                if (materialProduct.getId().equalsIgnoreCase(id)) {
                    materialSet.add(materialProduct);
                }
            }
        });
        basicProducts.forEach(basicProduct -> {
            log.debug("basicProduct id = {}", basicProduct.getId());
            for (String id : basicIds) {
                log.debug("id = {}", id);
                if (basicProduct.getId().equalsIgnoreCase(id)) {
                    basicSet.add(basicProduct);
                }
            }
        });
        if (materialSet.size() != materialIds.length) {
            throw new RuntimeException("some id not found in materials");
        }
        if (basicProducts.size() != basicIds.length) {
            throw new RuntimeException("some id not found in basics");
        }
        middle.setMaterialProducts(materialSet);
        middle.setBasicProducts(basicSet);
        return middle;
    }

    /**
     * 对象直接转换成文件
     *
     * @param middles 对象
     * @return 文本
     */
    private String[] to(List<Middle> middles) {
        List<String> contents = new LinkedList<>();
        middles.forEach(material -> contents.add(material.toString()));
        return ParseUtils.toArray(contents);
    }

    /**
     * 从文件读取的内容转换成对象
     *
     * @param contents 文件内容
     * @return 对象列表
     */
    private List<Middle> from(List<String> contents) {
        List<Middle> middles = new LinkedList<>();
        contents.forEach(content -> middles.add(getMiddle(content)));
        return middles;
    }

    private List<Middle> from() {
        return from(readFromFile());
    }

    // 查询
    public List<Middle> query() {
        setParam();
        List<String> contents = readFromFile();
        List<Middle> basics = new LinkedList<>();
        contents.forEach(content -> basics.add(getMiddle(content)));
        return basics;
    }

    // 增加
    public void add(Middle middle) throws IOException {
        setParam();
        List<Middle> middles = from();
        // 如果没有UUID, 则增加一个UUID
        if (null == middle.getId()) {
            middle.setId(getUUID());
        }
        middles.add(middle);
        String[] contents = to(middles);
        txtUtils.write(path, contents, charset, false, true);
    }

    // 删除
    public void remove(String id) throws IOException {
        setParam();
        List<Middle> middles = from();
        middles.removeIf(middle -> middle.getId().equals(id));
        String[] contents = to(middles);
        txtUtils.write(path, contents, charset, false, true);
    }

    // 更新
    public void update(Middle middle) throws IOException {
        setParam();
        List<Middle> middles = from();
        for (Middle m : middles) {
            // 只能够根据ID相同来进行更新，必须传入ID值
            if (m.getId().equals(middle.getId())) {
                updateUtil.copy(middle, m);
            }
        }
        String[] contents = to(middles);
        txtUtils.write(path, contents, charset, false, true);
    }

}
