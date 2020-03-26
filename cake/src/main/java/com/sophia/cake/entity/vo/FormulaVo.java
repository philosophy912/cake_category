package com.sophia.cake.entity.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author lizhe
 * @date 2020-03-24 22:32
 */
@Setter
@Getter
@ToString
public class FormulaVo {
    /**
     * 编号
     */
    private Integer fid;
    /**
     * 数量
     */
    private Float count;
    /**
     * 总价
     */
    private Float price;
    /**
     * 类别，默认原材料
     */
    private String type;
    /**
     * 对应的基础产品或者原材料的名字
     */
    private String name;
    /**
     * 对应的基础产品或者原材料的价格
     */
    private Float formulaPrice;
    /**
     * 对应的基础产品或者原材料的ID
     */
    private Integer id;
    /**
     * 对应的中级产品或者基础产品的ID
     */
    private Integer pid;
}
