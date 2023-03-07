package com.noobzy.idempotence.autoconfigure;

import com.noobzy.idempotence.token.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

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




}
