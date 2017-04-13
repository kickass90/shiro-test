package com.jet.dsm.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * redis 客户端封装
 * @Author: zhangkaifeng.
 * @CreateTime: 2017/4/12 16:03
 * @Description:开启服务命令----->(./redis-server /usr/redis-3.2.8/redis.conf &)
 */
@Component
public class RedisClient  {
    /**
     * 日志记录
     */
    private static final Logger LOG = LoggerFactory.getLogger(RedisClient.class);
    /**
     * redis 连接池
     */
    @Autowired
    private JedisPool pool;

    /**
     * 获取jedis
     * @return
     */
    public Jedis getResource(){
        Jedis jedis =null;
        try {
            jedis =pool.getResource();
            LOG.info("get  the redis resource success");
        } catch (Exception e) {
            e.printStackTrace();
            LOG.info("can't  get  the redis resource");

        }
        return jedis;
    }
    /**
     * 关闭连接
     * @param jedis
     */
    public void disconnect(Jedis jedis){
        jedis.disconnect();
    }
    /**
     * 将jedis 返还连接池
     * @param jedis
     */
    public void returnResource(Jedis jedis){
        if(null != jedis){
            try {
                pool.returnResource(jedis);
            } catch (Exception e) {
                LOG.info("can't return jedis to jedisPool");
            }
        }
    }
    /**
     * 无法返还jedispool，释放jedis客户端对象，
     * @param jedis
     */
    public void brokenResource(Jedis jedis){
        if (jedis!=null) {
            try {
                pool.returnBrokenResource(jedis);
            } catch (Exception e) {
                LOG.info("can't release jedis Object");
            }
        }
    }
}
