package com.noobzy.idempotence.token;

public interface TokenStorage {

    /**
     * 设置生成的token
     * @param token
     * @return true-代表设置成功，false-代表设置失败，一般原因为重复或其它异常
     */
     boolean setToken(String token);

    /**
     * 检查token是否存在，存在则删除，需要保证操作原子性
     * @param token
     * @return true-代表token存在有效 false-代表token不存在（已处理或过期如果有时间限制）或其它异常
     */
     boolean checkAndDelete(String token);

}
