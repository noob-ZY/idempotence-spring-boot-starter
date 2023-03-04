package org.example.idempotence.autoconfigure;

import org.example.idempotence.api.DefaultTokenApi;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class DefaultTokenApiAutoConfiguration {

    @Bean
    @ConditionalOnProperty(name = "idmp.api.enable", havingValue = "true")
    public DefaultTokenApi defaultTokenApi() {
        return new DefaultTokenApi();
    }

}
