package com.jet.dsm.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Author: zhangkaifeng.
 * @CreateTime: 2017/4/12 16:12
 * @Description:
 */

@Configuration
public class RedisConfig {

    @Autowired
    private JedisPoolConfig jedisPoolConfig;

    @Bean
    public JedisPoolConfig getJedisPoolConfig(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(100);
        jedisPoolConfig.setMaxIdle(25);
        jedisPoolConfig.setMaxWaitMillis(15000);
        jedisPoolConfig.setTestOnBorrow(false);
        jedisPoolConfig.setTestOnReturn(false);
        return jedisPoolConfig;
    }

    @Bean
    public JedisPool getJedisPool(){
        return  new JedisPool(jedisPoolConfig,"192.168.1.154",6379,2000,"123456");
    }
}
