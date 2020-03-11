package com.sophia.cake.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author lizhe
 * @date 2020/3/11 9:20
 **/
@Setter
@Getter
public class Material implements Serializable {
    /**
     * 编号
     */
    private Integer id;
    /**
     * 名字
     */
    private String name;
    /**
     * 容量
     */
    private Integer capacity;
    /**
     * 容量单位
     */
    private String unit;
    /**
     * 单价
     */
    private Float price;
    /**
     * 每单位价格
     */
    private Float pricePerUnit;

}
