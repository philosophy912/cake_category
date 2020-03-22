package com.sophia.cake.entity.bo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author lizhe
 * @date 2020-03-18 22:30
 */
@Setter
@Getter
@ToString
public class MaterialFormulaBo implements Serializable {

    private Integer id;
    private Float count;
    private Float price;
    private Integer materialId;
    private Integer middleId;
    private Integer basicId;

}
