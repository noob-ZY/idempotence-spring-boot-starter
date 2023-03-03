package org.example.idempotence.token;

public interface TokenUtil {

    String getToken();

    boolean checkToken(String token);

}
