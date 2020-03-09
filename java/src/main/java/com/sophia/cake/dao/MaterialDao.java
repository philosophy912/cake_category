package com.sophia.cake.dao;

import com.philosophy.base.util.ParseUtils;
import com.sophia.cake.entity.Material;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static com.sophia.cake.api.IEntity.COMMA;

/**
 * @author lizhe
 * @date 2020-03-08 22:13
 */
@Component
@Slf4j
public class MaterialDao extends BaseDao {


    private void setParam() {
        if (null == path) {
            path = dbUtils.getPaths(baseConfigure.getMaterial());
        }
        if (null == charset) {
            charset = baseConfigure.getCharset();
        }
    }


    private Material getMaterial(String content) {
        String[] values = content.split(COMMA);
        Material material = new Material();
        // id + COMMA + name + COMMA + capacity + COMMA + capacityType + COMMA + price + COMMA + pricePerCapacity;
        material.setId(values[0]);
        material.setName(values[1]);
        material.setCapacity(Integer.parseInt(values[2]));
        material.setCapacityType(values[3]);
        material.setPrice(Float.parseFloat(values[4]));
        material.setPricePerCapacity(Float.parseFloat(values[5]));
        return material;
    }

    /**
     * 对象直接转换成文件
     *
     * @param materials 对象
     * @return 文本
     */
    private String[] to(List<Material> materials) {
        List<String> contents = new LinkedList<>();
        materials.forEach(material -> contents.add(material.toString()));
        return ParseUtils.toArray(contents);
    }

    /**
     * 从文件读取的内容转换成对象
     *
     * @param contents 文件内容
     * @return 对象列表
     */
    private List<Material> from(List<String> contents) {
        List<Material> materials = new LinkedList<>();
        contents.forEach(content -> materials.add(getMaterial(content)));
        return materials;
    }

    private List<Material> from() {
        return from(readFromFile());
    }

    // 查询
    public List<Material> query() {
        setParam();
        List<String> contents = readFromFile();
        List<Material> materials = new LinkedList<>();
        contents.forEach(content -> materials.add(getMaterial(content)));
        return materials;
    }

    // 增加
    public void add(Material material) throws IOException {
        setParam();
        List<Material> materials = from();
        // 如果没有UUID, 则增加一个UUID
        if (null == material.getId()) {
            material.setId(getUUID());
        }
        materials.add(material);
        String[] contents = to(materials);
        txtUtils.write(path, contents, charset, false, true);
    }

    // 删除
    public void remove(String id) throws IOException {
        setParam();
        List<Material> materials = from();
        materials.removeIf(material -> material.getId().equals(id));
        String[] contents = to(materials);
        txtUtils.write(path, contents, charset, false, true);
    }

    // 更新
    public void update(Material material) throws IOException {
        setParam();
        List<Material> materials = from();
        for (Material m : materials) {
            // 只能够根据ID相同来进行更新，必须传入ID值
            if (m.getId().equals(material.getId())) {
                updateUtil.copy(material, m);
            }
        }
        String[] contents = to(materials);
        txtUtils.write(path, contents, charset, false, true);
    }

}
