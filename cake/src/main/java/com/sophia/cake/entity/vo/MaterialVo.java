package com.sophia.cake.entity.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author lizhe
 * @date 2020-03-24 22:19
 */
@Setter
@Getter
@ToString
public class MaterialVo {
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
