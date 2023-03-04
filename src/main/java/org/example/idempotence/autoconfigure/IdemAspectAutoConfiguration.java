package org.example.idempotence.autoconfigure;

import org.aspectj.lang.annotation.Aspect;
import org.example.idempotence.aspect.IdempotenceAspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;

public class IdemAspectAutoConfiguration {

    @Bean
    @ConditionalOnClass(Aspect.class)
    public IdempotenceAspect idempotenceAspect() {
        return new IdempotenceAspect();
    }

}
