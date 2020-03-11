package com.sophia.cake.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author lizhe
 * @date 2020/3/11 9:21
 **/
@Setter
@Getter
public class BasicFormula implements Serializable {

    /**
     * 编号
     */
    private Integer id;
    /**
     * 数量
     */
    private Float count;
    /**
     * 总价
     */
    private Float price;
    /**
     * 对应的基础产品
     */
    private Basic basic;

}
