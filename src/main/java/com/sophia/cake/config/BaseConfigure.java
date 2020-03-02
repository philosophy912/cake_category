package com.sophia.cake.config;

import com.sophia.cake.utils.UpdateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lizhe
 * @date 2020-03-02 22:16
 */
@Configuration
@Slf4j
public class BaseConfigure {

    @Bean
    public UpdateUtil getUpdateUtil(){
        return new UpdateUtil();
    }
}
