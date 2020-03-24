package com.sophia.cake.entity.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * @author lizhe
 * @date 2020-03-21 18:23
 */
@Setter
@Getter
public class BasicVo {

    private Integer id;
    // 名称
    private String name;
    // 单位
    private String unit;
    // 总量
    private Integer count;
    // 总价
    private Float totalPrice;
    // 包含的原材料
    private Set<MaterialFormulaVo> materials = new HashSet<>();
}
