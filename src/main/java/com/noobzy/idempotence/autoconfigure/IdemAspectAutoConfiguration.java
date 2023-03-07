package com.noobzy.idempotence.autoconfigure;

import com.noobzy.idempotence.aspect.IdempotenceAspect;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;

public class IdemAspectAutoConfiguration {

    @Bean
    @ConditionalOnClass(Aspect.class)
    public IdempotenceAspect idempotenceAspect() {
        return new IdempotenceAspect();
    }

}
