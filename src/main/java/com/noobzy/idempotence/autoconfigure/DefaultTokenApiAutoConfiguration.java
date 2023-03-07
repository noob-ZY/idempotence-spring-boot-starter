package com.noobzy.idempotence.autoconfigure;

import com.noobzy.idempotence.api.DefaultTokenApi;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

public class DefaultTokenApiAutoConfiguration {

    @Bean
    @ConditionalOnProperty(name = "idmp.api.enable", havingValue = "true")
    public DefaultTokenApi defaultTokenApi() {
        return new DefaultTokenApi();
    }

}
