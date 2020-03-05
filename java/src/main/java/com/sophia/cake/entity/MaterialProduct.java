package com.sophia.cake.entity;

import lombok.Getter;
import lombok.Setter;

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
 * @date 2020-03-04 22:10
 */
@Entity
@Setter
@Getter
@Table(name = "T_MATERIAL_PRODUCT")
public class MaterialProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    /**
     * 原材料数量
     */
    @Column(name = "count", nullable = true)
    private Float count;
    /**
     * 总价
     */
    @Column(name = "total_price", nullable = true)
    private Float totalPrice;

    @OneToOne
    @JoinColumn(name = "material")
    private Material material;

    public void update() {
        material.update();
        totalPrice = count * material.getPricePerCapacity();
    }


    @Override
    public String toString() {
        return "MaterialProduct{" +
                "id=" + id +
                ", count=" + count +
                ", totalPrice=" + totalPrice +
                ", material=" + material +
                '}';
    }
}
