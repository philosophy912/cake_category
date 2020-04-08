package com.sophia.cake.entity.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author lizhe
 * @date 2020-04-08 21:18
 */
@Setter
@Getter
@ToString
@ApiModel
public class NameBo extends EntityBo {
    @ApiModelProperty(value = "查询的名字")
    private String name;

}
