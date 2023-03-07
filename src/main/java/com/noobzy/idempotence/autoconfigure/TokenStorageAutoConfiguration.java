package com.noobzy.idempotence.autoconfigure;

import com.noobzy.idempotence.token.TokenStorage;
import com.noobzy.idempotence.token.RedisTokenStorage;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;

public class TokenStorageAutoConfiguration {

    @Bean
    @ConditionalOnProperty(name = "idmp.storage.type", havingValue = "redis")
    @ConditionalOnClass(RedisTemplate.class)
    public TokenStorage redisTokenStorage() {
        return new RedisTokenStorage();
    }

    //TODO
    @Bean
    @ConditionalOnProperty(name = "idmp.storage.type", havingValue = "mysql")
    public TokenStorage mysqlTokenStorage() {
        return null;
    }


}
