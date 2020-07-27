package com.atguigu.springcloud.config;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

/**
 * redicache 工具类
 *
 */
@SuppressWarnings("unchecked")
@Component
public class RedisUtils {
    @SuppressWarnings("rawtypes")
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 批量删除对应的value
     *
     * @param keys
     */
    public void remove(final String... keys) {
        for (String key : keys) {
            remove(key);
        }
    }

    /**
     * 批量删除key
     *
     * @param pattern
     */
    public void removePattern(final String pattern) {
        Set<Serializable> keys = redisTemplate.keys(pattern);
        if (keys.size() > 0)
            redisTemplate.delete(keys);
    }

    public void removeByPrex(final String pattern) {
        Set<Serializable> keys = redisTemplate.keys(pattern + "*");
        if (keys.size() > 0)
            redisTemplate.delete(keys);
    }

    public void removeBySuffix(final String pattern) {
        Set<Serializable> keys = redisTemplate.keys("*" + pattern);
        if (keys.size() > 0)
            redisTemplate.delete(keys);
    }

    /**
     * 删除对应的value
     *
     * @param key
     */
    public void remove(final String key) {
        if (exists(key)) {
            redisTemplate.delete(key);
        }
    }

    /**
     * 判断缓存中是否有对应的value
     *
     * @param key
     * @return
     */
    public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 读取缓存
     *
     * @param key
     * @return
     */
    public Object get(final String key) {
        Object result = null;
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        result = operations.get(key);
        return result;
    }

    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, Object value) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, Object value, Long expireTime) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean setHash(final String key, String hashKey,String value){
        boolean result = false;
        try {
            HashOperations<String, String, String> operations = redisTemplate.opsForHash();
            operations.put(key, hashKey, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;

    }
    public String getHash(final String key, String hashKey){
        String value = null;
        try {
            HashOperations<String, String, String> operations = redisTemplate.opsForHash();
            value = operations.get(key, hashKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    public List<String> getHash(final String key){
        List<String> list = null;
        try {
            HashOperations<String, String, String> operations = redisTemplate.opsForHash();
            list = operations.values(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
