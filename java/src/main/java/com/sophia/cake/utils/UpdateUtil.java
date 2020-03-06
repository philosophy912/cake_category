package com.sophia.cake.utils;

import com.sophia.cake.entity.Basic;
import com.sophia.cake.entity.BasicProduct;
import com.sophia.cake.entity.Material;
import com.sophia.cake.entity.MaterialProduct;
import com.sophia.cake.entity.Middle;
import lombok.SneakyThrows;

import java.lang.reflect.Field;

/**
 * @author lizhe
 * @date 2020-03-02 22:11
 */
public class UpdateUtil {


    /**
     * 把source中不为空的字段拷贝到target中
     *
     * @param source 源对象
     * @param target 目标对象
     */
    @SneakyThrows
    public void copy(Material source, Material target) {
        Field[] fields = source.getClass().getDeclaredFields();
        for (Field field : fields) {
            String name = field.getName();
            field.setAccessible(true);
            Object value = field.get(source);
            if (value != null) {
                Field targetField = target.getClass().getDeclaredField(name);
                targetField.setAccessible(true);
                targetField.set(target, value);
            }
        }
    }

    public void copy(MaterialProduct source, MaterialProduct target) {

    }

    public void copy(BasicProduct source, BasicProduct target){

    }

    public void copy(Middle source, Middle target){

    }

    public void copy(Basic source, Basic target) {
        Field[] fields = source.getClass().getDeclaredFields();
        for (Field field : fields) {
            String name = field.getName();
            field.setAccessible(true);

        }
    }

}
