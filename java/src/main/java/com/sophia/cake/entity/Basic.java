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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

/**
 * @author lizhe
 * @Description description
 * @date 2020/3/6 10:12
 **/
@Entity
@Table(name = "T_BASIC")
@Setter
@Getter
public class Basic extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 基础产品名称
     */
    @Column(name = "NAME", nullable = true)
    private String name;

    /**
     * 总价
     */
    @Column(name = "TOTAL_PRICE", nullable = true)
    private Float totalPrice;

    /**
     * 一种基础材料产品只对应一种基础产品
     * ONE2ONE
     * 关联关系由Basic维护
     */
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "basic_product_id")
    private BasicProduct basicProduct;


    /**
     * 一种基础材料可以有多个原材料
     * One2Many
     * 主键由One2Many维护
     */
    @OneToMany(mappedBy = "basic")
    private Set<MaterialProduct> materialProducts = new HashSet<>();

    public void update() {
        for (MaterialProduct product : materialProducts) {
            if (null != product) {
                product.update();
                totalPrice += product.getTotalPrice();
            }
        }
    }

    @Override
    public String toString() {
        return "Basic{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", totalPrice=" + totalPrice +
                ", basicProduct=" + basicProduct +
                ", materialProducts=" + materialProducts +
                '}';
    }
}
