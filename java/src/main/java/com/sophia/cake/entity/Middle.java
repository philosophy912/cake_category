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
import javax.persistence.OneToMany;
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
@Table(name = "T_MIDDLE")
public class Middle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    /**
     * 中级产品名称
     */
    @Column(name = "NAME", nullable = true)
    private String name;
    /**
     * 总价
     */
    @Column(name = "TOTAL_PRICE", nullable = true)
    private Float totalPrice;
    /**
     * 包含的材料
     */
    @JoinColumn(name = "MATERIAL_PRODUCT")
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private Set<MaterialProduct> materials = new HashSet<>();

    /**
     * 包含的材料
     */
    @JoinColumn(name = "BASIC_PRODUCT")
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private Set<BasicProduct> basicProducts = new HashSet<>();


    public void update() {
        for (MaterialProduct product : materials) {
            product.update();
            totalPrice += product.getTotalPrice();
        }
        for (BasicProduct product : basicProducts) {
            product.update();
            totalPrice += product.getTotalPrice();
        }
    }


    @Override
    public String toString() {
        return "Middle{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", totalPrice=" + totalPrice +
                ", materials=" + materials +
                ", basicProducts=" + basicProducts +
                '}';
    }
}
