package com.sophia.cake.entity.bo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author lizhe
 * @date 2020/4/7 10:26
 **/
@Setter
@Getter
@ToString
public class FormulaBo {
    /**
     * 编号
     */
    private Integer id;
    /**
     * 数量
     */
    private Float count;
    /**
     * 原材料编号
     */
    private Integer material_id;
    /**
     * 基础产品编号
     */
    private Integer middle_id;
    /**
     * 中级产品编号
     */
    private Integer basic_id;
}
