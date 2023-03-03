package org.example.idempotence.token;

public class TokenUtilImpl implements TokenUtil {

    protected TokenGenerator tokenGenerator;

    protected TokenStorage tokenStorage;

    public TokenUtilImpl(TokenGenerator tokenGenerator, TokenStorage tokenStorage) {
        this.tokenGenerator = tokenGenerator;
        this.tokenStorage = tokenStorage;
    }

    @Override
    public String getToken() {
        String token;

        try {
            token = tokenGenerator.genToken();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (!tokenStorage.setToken(token)) {
            throw new RuntimeException("token set failed");
        }

        return token;
    }

    @Override
    public boolean checkToken(String token) {
        return tokenStorage.checkAndDelete(token);
    }
}
