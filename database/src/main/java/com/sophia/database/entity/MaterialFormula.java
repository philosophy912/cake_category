package com.sophia.database.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author lizhe
 * @date 2020/3/11 9:20
 **/
@Setter
@Getter
@Entity
@Table(name = "T_MATERIAL_FORMULA")
public class MaterialFormula implements Serializable {
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
     * 对应的原材料
     * MaterialFormula中的id字段参考Material中的id字段
     */
    @OneToOne
    @JoinColumn(name = "material_id", referencedColumnName = "id")
    private Material material;

}
