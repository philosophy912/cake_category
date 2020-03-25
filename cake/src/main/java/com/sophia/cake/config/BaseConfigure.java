package com.sophia.cake.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author lizhe
 * @date 2020/3/11 11:43
 **/
@Component
@ConfigurationProperties(prefix = "config")
@Setter
@Getter
public class BaseConfigure {
    private String excel;
}
