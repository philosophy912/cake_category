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
 * @date 2020/3/6 10:17
 **/
@Setter
@Getter
@Entity
@Table(name = "T_BASIC_PRODUCT")
public class BasicProduct extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    /**
     * 一种基础材料产品只对应一种基础产品
     * ONE2ONE
     * 关联关系由Basic维护
     */
    @OneToOne(mappedBy = "basicProduct")
    private Basic basic;


    /**
     * 多种基础材料对应一个中级产品
     * One2Many
     * 主键由One2Many维护
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinColumn(name = "middle_id")
    private Middle middle;

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
                ", middle=" + middle +
                '}';
    }
}
