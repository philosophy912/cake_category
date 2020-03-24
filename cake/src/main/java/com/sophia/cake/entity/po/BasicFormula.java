package com.sophia.cake.entity.po;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author lizhe
 * @date 2020/3/11 9:21
 **/
@Setter
@Getter
@Entity
@Table(name = "T_BASIC_FORMULA")
@ToString
@JsonIgnoreProperties(value = {"handler"})
public class BasicFormula implements Serializable {

    /**
     * 编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 数量
     */
    @Column(name = "count", nullable = false)
    private Float count;
    /**
     * 总价
     */
    @Column(name = "price", nullable = false)
    private Float price;
    /**
     * 类别，默认基础材料
     */
    @Column(name = "type", nullable = false)
    private String type = "基础产品";
    /**
     * 对应的基础产品
     */
    @OneToOne
    @JoinColumn(name = "basic_id", referencedColumnName = "id")
    private Basic basic;

}
