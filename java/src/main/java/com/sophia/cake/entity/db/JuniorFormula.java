package com.sophia.cake.entity.db;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author lizhe
 * @date 2020/3/10 16:15
 **/
@Setter
@Getter
@Entity
@Table(name = "T_JUNIOR_FORMULA")
public class JuniorFormula {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    /**
     * 原材料数量
     */
    @Column(name = "count", nullable = false)
    private Float count;
    /**
     * 总价
     */
    @Column(name = "total_price", nullable = false)
    private Float totalPrice;




}
