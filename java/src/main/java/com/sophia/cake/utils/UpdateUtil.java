package com.sophia.cake.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author lizhe
 * @date 2020-03-02 22:11
 */
public class UpdateUtil {
    /**
     * 所有为空值的属性都不copy
     *
     * @param source 源对象
     * @param target 目标对象
     */
    public void copyNullProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target, getNullField(source));
    }
    /**
     * 获取属性中为空的字段
     *
     * @param target 目标对象
     * @return
     */
    private String[] getNullField(Object target) {
        BeanWrapper beanWrapper = new BeanWrapperImpl(target);
        PropertyDescriptor[] propertyDescriptors = beanWrapper.getPropertyDescriptors();
        Set<String> notNullFieldSet = new HashSet<>();
        if (propertyDescriptors.length > 0) {
            for (PropertyDescriptor p : propertyDescriptors) {
                String name = p.getName();
                Object value = beanWrapper.getPropertyValue(name);
                if (Objects.isNull(value)) {
                    notNullFieldSet.add(name);
                }
            }
        }
        String[] notNullField = new String[notNullFieldSet.size()];
        return notNullFieldSet.toArray(notNullField);
    }
}
