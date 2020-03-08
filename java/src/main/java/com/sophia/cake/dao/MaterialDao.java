package com.sophia.cake.dao;

import com.philosophy.base.util.ParseUtils;
import com.sophia.cake.entity.Material;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

import static com.sophia.cake.api.IEntity.COMMA;

/**
 * @author lizhe
 * @date 2020-03-08 22:13
 */
@Component
public class MaterialDao extends BaseDao {

    private Path path = dbUtils.getPaths(baseConfigure.getMaterial());
    private String charset = baseConfigure.getCharset();

    @SneakyThrows
    private List<String> readFromFile() {
        return txtUtils.read(path, charset, true);
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
     * ����ֱ��ת�����ļ�
     *
     * @param materials ����
     * @return �ı�
     */
    private String[] to(List<Material> materials) {
        List<String> contents = new LinkedList<>();
        materials.forEach(material -> contents.add(material.toString()));
        return ParseUtils.toArray(contents);
    }

    /**
     * ���ļ���ȡ������ת���ɶ���
     *
     * @param contents �ļ�����
     * @return �����б�
     */
    private List<Material> from(List<String> contents) {
        List<Material> materials = new LinkedList<>();
        contents.forEach(content -> materials.add(getMaterial(content)));
        return materials;
    }

    private List<Material> from() {
        return from(readFromFile());
    }

    // ��ѯ
    public List<Material> query() {
        List<String> contents = readFromFile();
        List<Material> materials = new LinkedList<>();
        contents.forEach(content -> materials.add(getMaterial(content)));
        return materials;
    }

    // ����
    public void add(Material material) throws IOException {
        List<Material> materials = from();
        // ���û��UUID, ������һ��UUID
        if (null == material.getId()) {
            material.setId(getUUID());
        }
        materials.add(material);
        String[] contents = to(materials);
        txtUtils.write(path, contents, charset, true, true);
    }

    // ɾ��
    public void remote(String id) throws IOException {
        List<Material> materials = from();
        materials.removeIf(material -> material.getId().equals(id));
        String[] contents = to(materials);
        txtUtils.write(path, contents, charset, true, true);
    }

    // ����
    public void update(Material material) throws IOException {
        List<Material> materials = from();
        for (Material m : materials) {
            // ֻ�ܹ�����ID��ͬ�����и��£����봫��IDֵ
            if (m.getId().equals(material.getId())) {
                updateUtil.copy(material, m);
            }
        }
        String[] contents = to(materials);
        txtUtils.write(path, contents, charset, true, true);
    }

}
