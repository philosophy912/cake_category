package com.sophia.cake.entity.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author lizhe
 * @date 2020-03-24 22:28
 */
@Setter
@Getter
@ToString
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
@ApiModel
public class MiddleVo implements Serializable {
    /**
     * 编号
     */
    @ApiModelProperty(value = "编号")
    private Integer id;
    /**
     * 名字
     */
    @ApiModelProperty(value = "名字")
    private String name;
    /**
     * 单位
     */
    @ApiModelProperty(value = "单位")
    private String unit;
    /**
     * 总量
     */
    @ApiModelProperty(value = "总量")
    private Float capacity;
    /**
     * 总价
     */
    @ApiModelProperty(value = "总价")
    private Float price;
    /**
     * 对应的材料集合,包含原材料以及基础材料
     */
    @ApiModelProperty(value = "原材料/基础产品集合")
    private Set<FormulaVo> formulas = new HashSet<>();
}
