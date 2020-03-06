package com.sophia.cake.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author lizhe
 * @Description description
 * @date 2020/3/6 10:16
 **/
@Entity
@Setter
@Getter
@Table(name = "T_MATERIAL_PRODUCT")
public class MaterialProduct extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    /**
     * 一种材料产品只对应一种原材料
     * ONE2ONE
     * 关联关系有Material维护
     */
    @OneToOne(mappedBy = "materialProduct")
    private Material material;

    /**
     * 多种原材料产品对应一个基础产品
     * Many2One
     * 主键由One2Many维护
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinColumn(name = "basic_id")
    private Basic basic;


    /**
     * 多种原材料对应一个中级产品
     * One2Many
     * 主键由One2Many维护
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinColumn(name = "middle_id")
    private Middle middle;

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
                ", basic=" + basic +
                ", middle=" + middle +
                '}';
    }
}
