package org.example.idempotence.autoconfigure;

import org.example.idempotence.token.RedisTokenStorage;
import org.example.idempotence.token.TokenStorage;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

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
