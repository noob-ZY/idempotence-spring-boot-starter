package com.noobzy.idempotence.api;

import com.noobzy.idempotence.token.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultTokenApi {

    @Autowired
    private TokenUtil tokenUtil;

    @GetMapping("${idem.api.url:/tokenApi/token}")
    public String getToken() {
        return tokenUtil.getToken();
    }

}
