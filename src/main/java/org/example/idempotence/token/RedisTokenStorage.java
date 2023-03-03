package org.example.idempotence.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class RedisTokenStorage implements TokenStorage{

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public boolean setToken(String token) {
        return redisTemplate.opsForValue().setIfAbsent(token, "1", 30, TimeUnit.MINUTES);
    }

    @Override
    public boolean checkAndDelete(String token) {

        try {
            DefaultRedisScript<String> script = new DefaultRedisScript<>();
            script.setScriptText(
                    "local deleteRow = redis.call('del', KEYS[1])\n" +
                            "if deleteRow == 1 then\n" +
                            "   return 'OK'\n" +
                            "else\n" +
                            "   return 'FAILED'\n" +
                            "end\n");
            script.setResultType(String.class);

            String executeResult = redisTemplate.execute(script, Arrays.asList(token), new String[0]);

            return "OK".equals(executeResult) ? true : false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
}
