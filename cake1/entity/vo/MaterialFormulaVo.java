package com.sophia.cake.entity.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * @author lizhe
 * @date 2020-03-21 19:05
 */
@Setter
@Getter
public class MaterialFormulaVo {
    // material Formula 的ID
    private Integer id;
    // 原材料名称
    private String name;
    // 类比
    private String type = "原材料";
    // 数量
    private Float count;
    // 总价
    private Float price;
}
