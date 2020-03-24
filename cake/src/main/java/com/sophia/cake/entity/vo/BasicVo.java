package com.sophia.cake.entity.vo;

import com.sophia.cake.entity.po.MaterialFormula;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * @author lizhe
 * @date 2020-03-24 22:23
 */
@Setter
@Getter
@ToString
public class BasicVo {
    /**
     * 编号
     */
    private Integer id;
    /**
     * 名字
     */
    private String name;
    /**
     * 单位
     */
    private String unit;
    /**
     * 总量
     */
    private Float capacity;
    /**
     * 总价
     */
    private Float price;
    /**
     * 对应的原材料集合
     */
    private Set<FormulaVo> materials = new HashSet<>();
}
