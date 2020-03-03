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
    @Column(name = "NAME")
    private String name;

    /**
     * 容量
     */
    @Column(name = "CAPACITY")
    private Integer capacity;
    /**
     * 容量单位
     */
    @Column(name = "CAPACITY_TYPE")
    private String capacityType;
    /**
     * 单价
     */
    @Column(name = "PRICE")
    private Float price;
    /**
     * 每单位价格
     */
    @Column(name = "PRICE_PER_CAPACITY")
    private Float pricePerCapacity;


    @Override
    public String toString() {
        return "MaterialProduct{" +
                "id=" + id +
                ", capacity=" + capacity +
                ", capacityType='" + capacityType + '\'' +
                ", price=" + price +
                ", pricePerCapacity=" + pricePerCapacity +
                '}';
    }
}
