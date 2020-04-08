package com.sophia.cake.entity.bo;

import com.philosophy.base.entity.EnvData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author lizhe
 * @date 2020-04-08 21:46
 */
@Setter
@Getter
@ToString
@ApiModel
public class EntityBo {
    @ApiModelProperty(value = "分页相关")
    private EnvData envData = new EnvData();
}
