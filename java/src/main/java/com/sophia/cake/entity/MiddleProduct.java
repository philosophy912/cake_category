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
@Table(name = "T_MIDDLE_PRODUCT")
public class MiddleProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    /**
     * 总价
     */
    @Column(name = "TOTAL_PRICE")
    private Float totalPrice;
    /**
     * 包含的材料
     */
    @JoinColumn(name = "MATERIAL_PRODUCT")
    @OneToMany(fetch = FetchType.LAZY)
    private Set<MaterialProduct> materials = new HashSet<>();

    /**
     * 包含的材料
     */
    @JoinColumn(name = "BASIC_PRODUCT")
    @OneToMany(fetch = FetchType.LAZY)
    private Set<BasicProduct> basicProducts = new HashSet<>();


}