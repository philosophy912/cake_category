package com.sophia.cake.entity.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author lizhe
 * @date 2020-03-24 22:32
 * 如果遇到
 */
@Setter
@Getter
@ToString
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
@ApiModel
public class FormulaVo implements Serializable {
    /**
     * 编号
     */
    @ApiModelProperty(value = "编号")
    private Integer fid;
    /**
     * 数量
     */
    @ApiModelProperty(value = "数量")
    private Float count;
    /**
     * 总价
     */
    @ApiModelProperty(value = "总价")
    private Float price;
    /**
     * 类别，默认原材料
     */
    @ApiModelProperty(value = "类别")
    private String type;
    /**
     * 对应的基础产品或者原材料的名字
     */
    @ApiModelProperty(value = "(基础产品/原材料)名字")
    private String name;
    /**
     * 对应的基础产品或者原材料的价格
     */
    @ApiModelProperty(value = "(基础产品/原材料)价格")
    private Float formulaPrice;
    /**
     * 对应的基础产品或者原材料的ID
     */
    @ApiModelProperty(value = "(基础产品/原材料)ID")
    private Integer id;
    /**
     * 对应的中级产品或者基础产品的ID
     */
    @ApiModelProperty(value = "(基础产品/中级产品)ID")
    private Integer pid;

}
