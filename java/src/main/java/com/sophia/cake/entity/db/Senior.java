package com.sophia.cake.entity.db;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author lizhe
 * @date 2020/3/10 16:14
 **/
@Setter
@Getter
@Entity
@Table(name = "T_SENIOR")
public class Senior {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    /**
     * 基础产品名称
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * 总价
     */
    @Column(name = "total_price", nullable = false)
    private Float totalPrice;
}

