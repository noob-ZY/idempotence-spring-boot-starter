package org.example.idempotence.token;

import java.util.UUID;

public class UUIDTokenGenerator implements TokenGenerator{
    @Override
    public String genToken() throws Exception {
        return UUID.randomUUID().toString();
    }
}
