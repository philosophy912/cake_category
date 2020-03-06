package com.sophia.cake.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author lizhe
 * @Description description
 * @date 2020/3/6 10:09
 **/
@Entity
@Table(name = "T_MATERIAL")
@Setter
@Getter
public class Material extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    /**
     * 一种材料对应一个原材料
     * ONE2ONE
     * 关联关系有Material维护
     */
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "material_product_id")
    private MaterialProduct materialProduct;

    public void update() {
        if (null != price && null != capacity) {
            pricePerCapacity = price / capacity;
        }
    }

    @Override
    public String toString() {
        return "Material{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", capacity=" + capacity +
                ", capacityType='" + capacityType + '\'' +
                ", price=" + price +
                ", pricePerCapacity=" + pricePerCapacity +
                ", materialProduct=" + materialProduct +
                '}';
    }
}
