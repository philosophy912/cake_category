package com.sophia.cake.entity.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
public class BVo  implements Serializable {
    private Integer id;
    private String name;
}
