package com.sophia.cake.dao;

import com.philosophy.base.util.ParseUtils;
import com.sophia.cake.entity.MaterialProduct;
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
public class MaterialProductDao extends BaseDao {

    private MaterialDao materialDao;

    @Autowired
    public void setMaterialDao(MaterialDao materialDao) {
        this.materialDao = materialDao;
    }


    private void setParam() {
        if (null == path) {
            path = dbUtils.getPaths(baseConfigure.getMaterialProduct());
        }
        if (null == charset) {
            charset = baseConfigure.getCharset();
        }
    }


    private MaterialProduct getMaterialProduct(String content) {
        String[] values = content.split(COMMA);
        MaterialProduct materialProduct = new MaterialProduct();
        // id + COMMA + count + COMMA + totalPrice + COMMA + material.getId();
        materialProduct.setId(values[0]);
        materialProduct.setCount(Float.parseFloat(values[1]));
        materialProduct.setTotalPrice(Float.parseFloat(values[2]));
        String materialId = values[3];
        materialDao.query().forEach(material -> {
            if (material.getId().equalsIgnoreCase(materialId)) {
                materialProduct.setMaterial(material);
            }
        });
        if (null == materialProduct.getMaterial()) {
            throw new RuntimeException("materialId[" + materialId + "] not found");
        }
        return materialProduct;
    }

    /**
     * 对象直接转换成文件
     *
     * @param materialProducts 对象
     * @return 文本
     */
    private String[] to(List<MaterialProduct> materialProducts) {
        List<String> contents = new LinkedList<>();
        materialProducts.forEach(materialProduct -> contents.add(materialProduct.toString()));
        return ParseUtils.toArray(contents);
    }

    /**
     * 从文件读取的内容转换成对象
     *
     * @param contents 文件内容
     * @return 对象列表
     */
    private List<MaterialProduct> from(List<String> contents) {
        List<MaterialProduct> materialProducts = new LinkedList<>();
        contents.forEach(content -> materialProducts.add(getMaterialProduct(content)));
        return materialProducts;
    }

    private List<MaterialProduct> from() {
        return from(readFromFile());
    }

    // 查询
    public List<MaterialProduct> query() {
        setParam();
        List<String> contents = readFromFile();
        List<MaterialProduct> materialProducts = new LinkedList<>();
        contents.forEach(content -> materialProducts.add(getMaterialProduct(content)));
        return materialProducts;
    }

    // 增加
    public void add(MaterialProduct materialProduct) throws IOException {
        setParam();
        List<MaterialProduct> materialProducts = from();
        // 如果没有UUID, 则增加一个UUID
        if (null == materialProduct.getId()) {
            materialProduct.setId(getUUID());
        }
        materialProducts.add(materialProduct);
        String[] contents = to(materialProducts);
        txtUtils.write(path, contents, charset, false, true);
    }

    // 删除
    public void remove(String id) throws IOException {
        setParam();
        List<MaterialProduct> materials = from();
        materials.removeIf(material -> material.getId().equals(id));
        String[] contents = to(materials);
        txtUtils.write(path, contents, charset, false, true);
    }

    // 更新
    public void update(MaterialProduct materialProduct) throws IOException {
        setParam();
        List<MaterialProduct> materials = from();
        for (MaterialProduct m : materials) {
            // 只能够根据ID相同来进行更新，必须传入ID值
            if (m.getId().equals(materialProduct.getId())) {
                updateUtil.copy(materialProduct, m);
            }
        }
        String[] contents = to(materials);
        txtUtils.write(path, contents, charset, false, true);
    }

}
