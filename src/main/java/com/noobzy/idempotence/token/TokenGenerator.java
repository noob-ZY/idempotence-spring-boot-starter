package com.noobzy.idempotence.token;

public interface TokenGenerator {

    String genToken() throws Exception;

}
