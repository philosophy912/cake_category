package com.sophia.cake.entity;

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
 * @date 2020-02-25 9:39
 */
@Entity
@Table(name = "T_MATERIAL_PRODUCT")
@Setter
@Getter
public class MaterialProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * 原材料名字
     */
    @Column(name = "NAME", nullable = false)
    private String name;

    /**
     * 容量
     */
    @Column(name = "CAPACITY", nullable = false)
    private Integer capacity;
    /**
     * 容量单位
     */
    @Column(name = "CAPACITY_TYPE", nullable = false)
    private String capacityType;
    /**
     * 单价
     */
    @Column(name = "PRICE", nullable = false)
    private Float price;
    /**
     * 每单位价格
     */
    @Column(name = "PRICE_PER_CAPACITY", nullable = false)
    private Float pricePerCapacity;

    @Override
    public String toString() {
        return "MaterialProduct{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", capacity=" + capacity +
                ", capacityType='" + capacityType + '\'' +
                ", price=" + price +
                ", pricePerCapacity=" + pricePerCapacity +
                '}';
    }
}
