package com.sophia.cake.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author lizhe
 * @date 2020-03-05 23:05
 */
@Component
@ConfigurationProperties(prefix = "config")
@Setter
@Getter
public class BaseConfigure {

    private String excel;
    private String charset;
    private String material;
    private String materialProduct;
    private String basic;
}
