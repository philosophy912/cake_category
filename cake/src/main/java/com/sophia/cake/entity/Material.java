package com.sophia.cake.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author lizhe
 * @date 2020/3/11 9:20
 **/
@Setter
@Getter
@Entity
@Table(name = "T_MATERIAL")
@ToString
@JsonIgnoreProperties(value = {"handler"})
public class Material implements Serializable {
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
     * 容量
     */
    @Column(name = "capacity", nullable = false)
    private Integer capacity;
    /**
     * 容量单位
     */
    @Column(name = "unit", nullable = false)
    private String unit;
    /**
     * 单价
     */
    @Column(name = "price", nullable = false)
    private Float price;
    /**
     * 每单位价格
     */
    @Column(name = "price_per_unit", nullable = false)
    private Float pricePerUnit;

}
