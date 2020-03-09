package com.sophia.cake.dao;

import com.philosophy.base.util.ParseUtils;
import com.sophia.cake.entity.BasicProduct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static com.sophia.cake.api.IEntity.COMMA;

/**
 * @author lizhe
 * @date 2020/3/9 17:54
 **/
@Component
@Slf4j
public class BasicProductDao extends BaseDao {

    private BasicDao basicDao;

    @Autowired
    public void setBasicDao(BasicDao basicDao) {
        this.basicDao = basicDao;
    }

    private void setParam() {
        if (null == path) {
            path = dbUtils.getPaths(baseConfigure.getBasicProduct());
        }
        if (null == charset) {
            charset = baseConfigure.getCharset();
        }
    }


    private BasicProduct getBasicProduct(String content) {
        log.info("content is = {}", content);
        String[] values = content.split(COMMA);
        BasicProduct basicProduct = new BasicProduct();
        // id + COMMA + count + COMMA + totalPrice + COMMA + basic.getId();
        basicProduct.setId(values[0]);
        basicProduct.setCount(Float.parseFloat(values[1]));
        basicProduct.setTotalPrice(Float.parseFloat(values[2]));
        String basicId = values[3];
        basicDao.query().forEach(basic -> {
            if (basic.getId().equalsIgnoreCase(basicId)) {
                basicProduct.setBasic(basic);
            }
        });
        if (null == basicProduct.getBasic()) {
            throw new RuntimeException("basicId[" + basicId + "] not found");
        }
        return basicProduct;
    }

    /**
     * 对象直接转换成文件
     *
     * @param basicProducts 对象
     * @return 文本
     */
    private String[] to(List<BasicProduct> basicProducts) {
        List<String> contents = new LinkedList<>();
        basicProducts.forEach(basicProduct -> contents.add(basicProduct.toString()));
        return ParseUtils.toArray(contents);
    }

    /**
     * 从文件读取的内容转换成对象
     *
     * @param contents 文件内容
     * @return 对象列表
     */
    private List<BasicProduct> from(List<String> contents) {
        List<BasicProduct> basicProducts = new LinkedList<>();
        contents.forEach(content -> basicProducts.add(getBasicProduct(content)));
        return basicProducts;
    }

    private List<BasicProduct> from() {
        return from(readFromFile());
    }

    // 查询
    public List<BasicProduct> query() {
        setParam();
        List<String> contents = readFromFile();
        List<BasicProduct> basicProducts = new LinkedList<>();
        contents.forEach(content -> basicProducts.add(getBasicProduct(content)));
        return basicProducts;
    }

    // 增加
    public void add(BasicProduct basicProduct) throws IOException {
        setParam();
        List<BasicProduct> basicProducts = from();
        // 如果没有UUID, 则增加一个UUID
        if (null == basicProduct.getId()) {
            basicProduct.setId(getUUID());
        }
        basicProducts.add(basicProduct);
        String[] contents = to(basicProducts);
        txtUtils.write(path, contents, charset, false, true);
    }

    // 删除
    public void remove(String id) throws IOException {
        setParam();
        List<BasicProduct> basicProducts = from();
        basicProducts.removeIf(basicProduct -> basicProduct.getId().equals(id));
        String[] contents = to(basicProducts);
        txtUtils.write(path, contents, charset, false, true);
    }

    // 更新
    public void update(BasicProduct basicProduct) throws IOException {
        setParam();
        List<BasicProduct> materials = from();
        for (BasicProduct m : materials) {
            // 只能够根据ID相同来进行更新，必须传入ID值
            if (m.getId().equals(basicProduct.getId())) {
                updateUtil.copy(basicProduct, m);
            }
        }
        String[] contents = to(materials);
        txtUtils.write(path, contents, charset, false, true);
    }

}
