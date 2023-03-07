package com.noobzy.idempotence.aspect;

import com.noobzy.idempotence.token.TokenUtil;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import com.noobzy.idempotence.exception.RequestedTokenNotExistException;
import com.noobzy.idempotence.exception.TokenNotAvailableException;
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
