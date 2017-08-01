package com.jet.dsm.redis;

public interface CacheService {

    void put(String key, Object v);

    Object get(String key);

}
