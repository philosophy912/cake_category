package com.sophia.cake.entity.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author lizhe
 * @date 2020-03-24 22:23
 */
@Setter
@Getter
@ToString
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class BasicVo implements Serializable {
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
    private Set<FormulaVo> formulas = new HashSet<>();
}
