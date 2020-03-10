package com.sophia.cake.entity.db;

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
import java.util.Set;

/**
 * @author lizhe
 * @date 2020/3/10 16:16
 **/
@Setter
@Getter
@Entity
@Table(name = "T_SENIOR_FORMULA")
public class SeniorFormula {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    /**
     * 基础产品数量
     */
    @Column(name = "count", nullable = false)
    private Float count;
    /**
     * 总价
     */
    @Column(name = "total_price", nullable = false)
    private Float totalPrice;

    @OneToOne
    @JoinColumn(name = "senior_id", insertable = false, updatable = false)
    private Senior senior;

}
