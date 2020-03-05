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
 * @date 2020-03-04 22:18
 */
@Setter
@Getter
@Entity
@Table(name = "T_BASIC_PRODUCT")
public class BasicProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    /**
     * 基础产品数量
     */
    @Column(name = "count", nullable = true)
    private Float count;
    /**
     * 总价
     */
    @Column(name = "total_price", nullable = true)
    private Float totalPrice;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "basic")
    private Basic basic;


    public void update() {
        basic.update();
        totalPrice = count * basic.getTotalPrice();
    }

    @Override
    public String toString() {
        return "BasicProduct{" +
                "id=" + id +
                ", count=" + count +
                ", totalPrice=" + totalPrice +
                ", basic=" + basic +
                '}';
    }
}
