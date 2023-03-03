package org.example.idempotence.autoconfigure;

import org.example.idempotence.api.DefaultTokenApi;
import org.example.idempotence.token.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class IdempotenceAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(TokenUtil.class)
    public TokenUtil tokenUtil(TokenGenerator tokenGenerator, TokenStorage tokenStorage) {
        return new TokenUtilImpl(tokenGenerator, tokenStorage);
    }

    @Bean
    @ConditionalOnMissingBean(TokenGenerator.class)
    public TokenGenerator tokenGenerator() {
        return new UUIDTokenGenerator();
    }

    @Bean
    @ConditionalOnProperty(name = "idmp.storage.type", havingValue = "redis")
    @ConditionalOnBean(RedisTemplate.class)
    public TokenStorage redisTokenStorage() {
        return new RedisTokenStorage();
    }

    //TODO
    @Bean
    @ConditionalOnProperty(name = "idmp.storage.type", havingValue = "mysql")
    public TokenStorage mysqlTokenStorage() {
        return null;
    }

    @Bean
    @ConditionalOnProperty(name = "idmp.api.enable", havingValue = "true")
    public DefaultTokenApi defaultTokenApi() {
        return new DefaultTokenApi();
    }

}
