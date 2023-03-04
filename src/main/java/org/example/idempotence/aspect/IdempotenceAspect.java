package org.example.idempotence.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.example.idempotence.exception.RequestedTokenNotExistException;
import org.example.idempotence.exception.TokenNotAvailableException;
import org.example.idempotence.token.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
public class IdempotenceAspect {

    @Autowired
    private TokenUtil tokenUtil;

    @Pointcut("@annotation(org.example.idempotence.annotation.Idempotent)")
    public void pointCut(){};

    @Before("pointCut()")
    public void before() throws RuntimeException {
        System.out.println("before");
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String idempotenceToken = requestAttributes.getRequest().getHeader("idempotenceToken");

        if (null == idempotenceToken || idempotenceToken.trim().isEmpty()) {
            throw new RequestedTokenNotExistException();
        }

        if (!tokenUtil.checkToken(idempotenceToken)) {
            throw new TokenNotAvailableException();
        }

    }

}
