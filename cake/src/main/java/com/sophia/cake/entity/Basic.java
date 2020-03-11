package com.sophia.cake.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

/**
 * @author lizhe
 * @date 2020/3/11 9:21
 **/
@Setter
@Getter
public class Basic implements Serializable {
    /**
     * 编号
     */
    private Integer id;
    /**
     * 名字
     */
    private String name;
    /**
     * 总价
     */
    private Float price;
    /**
     * 对应的原材料集合
     */
    private Set<MaterialFormula> materialFormulaSet;

}
