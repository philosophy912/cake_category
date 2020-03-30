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
 * @date 2020/3/27 16:02
 **/
@Setter
@Getter
@ToString
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
@ApiModel
public class BVo  implements Serializable {
    @ApiModelProperty(value = "编号")
    private Integer id;
    @ApiModelProperty(value = "名字")
    private String name;
}
