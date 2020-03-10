package com.sophia.cake.utils;

import com.sophia.cake.entity.Basic;
import com.sophia.cake.entity.BasicProduct;
import com.sophia.cake.entity.Material;
import com.sophia.cake.entity.MaterialProduct;
import com.sophia.cake.entity.Middle;
import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Set;

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
            // 设置权限为可编辑
            field.setAccessible(true);
            Object value = field.get(source);
            // 判断对象是否为空
            if (value != null) {
                // 如果属性的类对象名字不属于BasicProduct和Set，就进行更新
                if (!(BasicProduct.class.isAssignableFrom(field.getType()))) {
                    Field targetField = target.getClass().getDeclaredField(field.getName());
                    targetField.setAccessible(true);
                    targetField.set(target, value);
                }
            }
        }
    }

    @SneakyThrows
    public void copy(MaterialProduct source, MaterialProduct target) {
        Field[] fields = source.getClass().getDeclaredFields();
        for (Field field : fields) {
            // 设置权限为可编辑
            field.setAccessible(true);
            Object value = field.get(source);
            // 判断对象是否为空
            if (value != null) {
                // 如果属性的类对象名字不属于BasicProduct和Set，就进行更新
                if (!(Material.class.isAssignableFrom(field.getType()) ||
                        Basic.class.isAssignableFrom(field.getType()) ||
                        Middle.class.isAssignableFrom(field.getType()))) {
                    Field targetField = target.getClass().getDeclaredField(field.getName());
                    targetField.setAccessible(true);
                    targetField.set(target, value);
                }
            }
        }
    }

    @SneakyThrows
    public void copy(BasicProduct source, BasicProduct target) {
        Field[] fields = source.getClass().getDeclaredFields();
        for (Field field : fields) {
            // 设置权限为可编辑
            field.setAccessible(true);
            Object value = field.get(source);
            // 判断对象是否为空
            if (value != null) {
                // 如果属性的类对象名字不属于BasicProduct和Set，就进行更新
                if (!(Basic.class.isAssignableFrom(field.getType()) ||
                        Middle.class.isAssignableFrom(field.getType()))) {
                    Field targetField = target.getClass().getDeclaredField(field.getName());
                    targetField.setAccessible(true);
                    targetField.set(target, value);
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    @SneakyThrows
    public void copy(Middle source, Middle target) {
        Set<MaterialProduct> materialProducts = target.getMaterialProducts();
        Set<BasicProduct> basicProducts = target.getBasicProducts();
        Field[] fields = source.getClass().getDeclaredFields();
        for (Field field : fields) {
            // 设置权限为可编辑
            field.setAccessible(true);
            Object value = field.get(source);
            // 判断对象是否为空
            if (value != null) {
                // 更新Set对象
                if (Set.class.isAssignableFrom(field.getType())) {
                    Method method = Set.class.getDeclaredMethod("iterator");
                    Iterator<Object> it = (Iterator<Object>) method.invoke(value);
                    while (it.hasNext()) {
                        Object element = it.next();
                        if (MaterialProduct.class.isAssignableFrom(element.getClass())) {
                            // 从target中根据ID来查找
                            MaterialProduct product = (MaterialProduct) element;
                            MaterialProduct targetProduct = getMaterialProductByID(product.getId(), materialProducts);
                            if (targetProduct != null) {
                                copy(product, targetProduct);
                            }
                        } else if (BasicProduct.class.isAssignableFrom(element.getClass())) {
                            BasicProduct product = (BasicProduct) element;
                            BasicProduct targetProduct = getBasicProductByID(product.getId(), basicProducts);
                            if (targetProduct != null) {
                                copy(product, targetProduct);
                            }
                        }
                    }
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    @SneakyThrows
    public void copy(Basic source, Basic target) {
        Set<MaterialProduct> materialProducts = target.getMaterialProducts();
        Field[] fields = source.getClass().getDeclaredFields();
        for (Field field : fields) {
            // 设置权限为可编辑
            field.setAccessible(true);
            Object value = field.get(source);
            // 判断对象是否为空
            if (value != null) {
                // 更新Set对象
                if (Set.class.isAssignableFrom(field.getType())) {
                    Method method = Set.class.getDeclaredMethod("iterator");
                    Iterator<Object> it = (Iterator<Object>) method.invoke(value);
                    while (it.hasNext()) {
                        Object element = it.next();
                        if (MaterialProduct.class.isAssignableFrom(element.getClass())) {
                            // 从target中根据ID来查找
                            MaterialProduct product = (MaterialProduct) element;
                            MaterialProduct targetProduct = getMaterialProductByID(product.getId(), materialProducts);
                            if (targetProduct != null) {
                                copy(product, targetProduct);
                            }
                        }
                    }
                }
            }
        }
    }


    public MaterialProduct getMaterialProductByID(String id, Set<MaterialProduct> materialProducts) {
        for (MaterialProduct product : materialProducts) {
            if (product.getId().equals(id)) {
                return product;
            }
        }
        return null;
    }


    public BasicProduct getBasicProductByID(String id, Set<BasicProduct> basicProducts) {
        for (BasicProduct product : basicProducts) {
            if (product.getId().equals(id)) {
                return product;
            }
        }
        return null;
    }
}
