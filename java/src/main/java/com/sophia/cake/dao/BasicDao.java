package com.sophia.cake.dao;

import com.philosophy.base.util.ParseUtils;
import com.sophia.cake.entity.Basic;
import com.sophia.cake.entity.Material;
import com.sophia.cake.entity.MaterialProduct;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Iterator;
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
public class BasicDao extends BaseDao {


    private MaterialProductDao materialProductDao;

    @Autowired
    public void setMaterialProductDao(MaterialProductDao materialProductDao) {
        this.materialProductDao = materialProductDao;
    }


    private Path path;
    private String charset;


    private void setParam() {
        if (null == path) {
            path = dbUtils.getPaths(baseConfigure.getMaterial());
        }
        if (null == charset) {
            charset = baseConfigure.getCharset();
        }
    }


    @SneakyThrows
    private List<String> readFromFile() {
        return txtUtils.read(path, charset, true);
    }


    private Basic getBasic(String content) {
        String[] values = content.split(COMMA);
        Basic basic = new Basic();
        // sb.append(id).append(COMMA).append(name).append(COMMA).append(totalPrice).append(COMMA);
        //  materialProducts.forEach(materialProduct -> sb.append(materialProduct.getId()).append(SEMICOLON));
        basic.setId(values[0]);
        basic.setName(values[1]);
        basic.setTotalPrice(Float.parseFloat(values[2]));
        String materialProductsIds = values[values.length - 1];
        String[] materialIds = materialProductsIds.split(SEMICOLON);
        Set<MaterialProduct> materialSet = new HashSet<>();
        List<MaterialProduct> materialProducts = materialProductDao.query();
        materialProducts.forEach(materialProduct -> {
            for (String id : materialIds) {
                if (materialProduct.getId().equalsIgnoreCase(id)) {
                    materialSet.add(materialProduct);
                }
            }
        });
        if (materialSet.size() != materialIds.length) {
            throw new RuntimeException("some id not found in materials");
        }
        basic.setMaterialProducts(materialSet);
        return basic;
    }

    /**
     * 对象直接转换成文件
     *
     * @param basics 对象
     * @return 文本
     */
    private String[] to(List<Basic> basics) {
        List<String> contents = new LinkedList<>();
        basics.forEach(material -> contents.add(material.toString()));
        return ParseUtils.toArray(contents);
    }

    /**
     * 从文件读取的内容转换成对象
     *
     * @param contents 文件内容
     * @return 对象列表
     */
    private List<Basic> from(List<String> contents) {
        List<Basic> materials = new LinkedList<>();
        contents.forEach(content -> materials.add(getBasic(content)));
        return materials;
    }

    private List<Basic> from() {
        return from(readFromFile());
    }

    // 查询
    public List<Basic> query() {
        setParam();
        List<String> contents = readFromFile();
        List<Basic> basics = new LinkedList<>();
        contents.forEach(content -> basics.add(getBasic(content)));
        return basics;
    }

    // 增加
    public void add(Basic basic) throws IOException {
        setParam();
        List<Basic> basics = from();
        // 如果没有UUID, 则增加一个UUID
        if (null == basic.getId()) {
            basic.setId(getUUID());
        }
        basics.add(basic);
        String[] contents = to(basics);
        log.info("contents length = {}", contents.length);
        txtUtils.write(path, contents, charset, false, true);
    }

    // 删除
    public void remove(String id) throws IOException {
        setParam();
        List<Basic> basics = from();
        basics.removeIf(material -> material.getId().equals(id));
        String[] contents = to(basics);
        txtUtils.write(path, contents, charset, false, true);
    }

    // 更新
    public void update(Basic basic) throws IOException {
        setParam();
        List<Basic> materials = from();
        for (Basic m : materials) {
            // 只能够根据ID相同来进行更新，必须传入ID值
            if (m.getId().equals(basic.getId())) {
                updateUtil.copy(basic, m);
            }
        }
        String[] contents = to(materials);
        txtUtils.write(path, contents, charset, false, true);
    }

}
