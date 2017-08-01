package com.jet.dsm.redis;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

/**
 * @Author: zhangkaifeng.
 * @CreateTime: 2017/7/31 11:14
 * @Description:
 */

@Service
public class RedisCacheServiceImpl implements CacheService{


    @Autowired
    private CacheManager cacheManager;


    @Override
    public void put(String key, Object v) {
        cacheManager.getCache("cache").put(key, v);
    }

    @Override
    public Object get(String key) {
        return cacheManager.getCache("cache").get(key);
    }
}
