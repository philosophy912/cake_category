package com.sophia.cake.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
 * @date 2020-02-25 9:40
 */
@Setter
@Getter
@Entity
@Table(name = "T_BASIC")
public class Basic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    /**
     * 初级产品名称
     */
    @Column(name = "NAME", nullable = true)
    private String name;

    /**
     * 总价
     */
    @Column(name = "TOTAL_PRICE", nullable = true)
    private Float totalPrice;

    @OneToOne(mappedBy = "basic")
    private BasicProduct basicProduct;

    /**
     * 包含的材料
     */
    @JoinColumn(name = "MATERIAL_PRODUCT", nullable = true)
    @OneToMany(fetch = FetchType.LAZY)
    private Set<MaterialProduct> materials = new HashSet<>();


    public void update() {
        for (MaterialProduct materialProduct : materials) {
            materialProduct.update();
            totalPrice += materialProduct.getTotalPrice();
        }
    }


    @Override
    public String toString() {
        return "Basic{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", totalPrice=" + totalPrice +
                ", basicProduct=" + basicProduct +
                ", materials=" + materials +
                '}';
    }
}
