package com.sophia.database.entity;

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
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author lizhe
 * @date 2020/3/11 9:21
 **/
@Setter
@Getter
@Entity
@Table(name = "T_MIDDLE")
public class Middle implements Serializable {
    /**
     * 编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 名字
     */
    @Column(name = "name", nullable = false)
    private String name;
    /**
     * 总价
     */
    @Column(name = "price", nullable = false)
    private Float price;
    /**
     * 对应的原材料集合
     */
    @OneToMany
    @JoinColumn(name = "material_formula_id")
    private Set<MaterialFormula> materialFormulaSet = new HashSet<>();

    /**
     * 对应的初级产品集合
     */
    @OneToMany
    @JoinColumn(name = "basic_formula_id")
    private Set<BasicFormula> basicFormulaSet = new HashSet<>();

}
