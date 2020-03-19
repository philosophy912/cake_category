package com.sophia.cake.entity.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author lizhe
 * @date 2020-03-19 21:21
 */
@Setter
@Getter
@ToString
public class BasicFormulaVo implements Serializable {

    private Integer id;
    private Float count;
    private Float price;
    private Integer basicId;
    private Integer middleId;
}
