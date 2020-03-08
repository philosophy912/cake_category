package com.sophia.cake.config;

import com.philosophy.txt.util.TxtUtils;
import com.sophia.cake.utils.DbUtils;
import com.sophia.cake.utils.UpdateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lizhe
 * @date 2020-03-08 22:19
 */
@Configuration
@Slf4j
public class BeanConfigure {

    @Bean
    public DbUtils getDbUtils() {
        return new DbUtils();
    }

    @Bean
    public TxtUtils getTxtUtils() {
        return new TxtUtils();
    }

    @Bean
    public UpdateUtil getUpdateUtil() {
        return new UpdateUtil();
    }
}
