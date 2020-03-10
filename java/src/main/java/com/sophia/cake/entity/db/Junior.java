package com.sophia.cake.entity.db;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author lizhe
 * @date 2020/3/10 16:13
 **/
@Setter
@Getter
@Entity
@Table(name = "T_JUNIOR")
public class Junior {
    /**
     * 唯一ID号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    /**
     * 原材料名字
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * 容量
     */
    @Column(name = "capacity", nullable = false)
    private Integer capacity;
    /**
     * 容量单位
     */
    @Column(name = "capacity_type")
    private String capacityType;
    /**
     * 单价
     */
    @Column(name = "price", nullable = false)
    private Float price;
    /**
     * 每单位价格
     */
    @Column(name = "price_per_capacity", nullable = false)
    private Float pricePerCapacity;
}
