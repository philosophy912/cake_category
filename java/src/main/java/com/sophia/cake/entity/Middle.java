package com.sophia.cake.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

/**
 * @author lizhe
 * @Description description
 * @date 2020/3/6 10:13
 **/
@Entity
@Table(name = "T_MIDDLE")
@Setter
@Getter
public class Middle extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
     * 一种中级材料可以有多个原材料
     * One2Many
     * 主键由One2Many维护
     */
    @OneToMany(mappedBy = "middle")
    private Set<MaterialProduct> materialProducts = new HashSet<>();

    /**
     * 一种中级材料可以有多个基础材料
     * One2Many
     * 主键由One2Many维护
     */
    @OneToMany(mappedBy = "middle")
    private Set<BasicProduct> basicProducts = new HashSet<>();

    public void update() {
        for (MaterialProduct product : materialProducts) {
            if (null != product) {
                product.update();
                totalPrice += product.getTotalPrice();
            }

        }
        for (BasicProduct product : basicProducts) {
            if (null != product) {
                product.update();
                totalPrice += product.getTotalPrice();
            }

        }
    }

    @Override
    public String toString() {
        return "Middle{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", totalPrice=" + totalPrice +
                ", materialProducts=" + materialProducts +
                ", basicProducts=" + basicProducts +
                '}';
    }
}
