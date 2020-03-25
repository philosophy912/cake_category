package com.sophia.cake.entity.vo;

import lombok.Getter;

/**
 * @author lizhe
 * @date 2020/3/25 15:27
 **/
public enum FormulaType {
    MATERIAL("原材料"),
    BASIC("基础材料");

    @Getter
    private String value;

    FormulaType(String value) {
        this.value = value;
    }

    /**
     * 根据值返回字符类型
     *
     * @param value 枚举值
     * @return 返回字符的枚举值
     */
    public static FormulaType fromValue(String value) {
        for (FormulaType type : values()) {
            if (type.value.equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new RuntimeException("not support character type[" + value + "]");
    }
}
