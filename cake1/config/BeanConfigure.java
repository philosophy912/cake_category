package com.sophia.cake.config;

import com.sophia.cake.utils.EntityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lizhe
 * @date 2020/3/11 11:41
 **/
@Configuration
@Slf4j
public class BeanConfigure {

    @Bean
    public EntityUtils getEntityUtils(){
        return new EntityUtils();
    }
}
