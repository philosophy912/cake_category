package com.sophia.cake.entity.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

/**
 * @author lizhe
 * @date 2020-03-24 22:28
 */
@Setter
@Getter
@ToString
public class MiddleVo {
    /**
     * 编号
     */
    private Integer id;
    /**
     * 名字
     */
    private String name;
    /**
     * 单位
     */
    private String unit;
    /**
     * 总量
     */
    private Float capacity;
    /**
     * 总价
     */
    private Float price;
    /**
     * 对应的材料集合,包含原材料以及基础材料
     */
    private Set<FormulaVo> formulas = new HashSet<>();
}
