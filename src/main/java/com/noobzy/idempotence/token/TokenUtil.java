package com.noobzy.idempotence.token;

public interface TokenUtil {

    String getToken();

    boolean checkToken(String token);

}
