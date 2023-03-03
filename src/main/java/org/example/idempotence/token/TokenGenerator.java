package org.example.idempotence.token;

public interface TokenGenerator {

    String genToken() throws Exception;

}
