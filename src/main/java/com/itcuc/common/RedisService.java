package com.itcuc.common;

import com.google.common.collect.Maps;
import com.itcuc.common.utils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Component
public class RedisService {
    @Autowired
    RedisTemplate<String,?> redisTemplate;

    /**
     * 设置string类型数据
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(String key, String value) {
        return redisTemplate.execute((RedisCallback<Boolean>) connection -> {
            RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
            return connection.set(serializer.serialize(key), serializer.serialize(value));
        });
    }

    /**
     * 获取string类型数据
     *
     * @param key
     * @return
     */
    public String get(String key) {
        return redisTemplate.execute((RedisCallback<String>) connection -> {
            RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
            byte[] value = connection.get(serializer.serialize(key));
            return serializer.deserialize(value);
        });
    }

    /**
     * 设置有效天数
     *
     * @param key
     * @param expire
     * @return
     */
    public boolean expire(String key, long expire) {
        return redisTemplate.expire(key, expire, TimeUnit.SECONDS);
    }

    /**
     * 移除数据
     *
     * @param key
     * @return
     */
    public boolean remove(String key) {
        return redisTemplate.execute((RedisCallback<Boolean>) connection -> {
            RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
            connection.del(key.getBytes());
            return true;
        });
    }

    /**
     * 设置hash类型数据
     * @param key
     * @param object
     * @return
     */
    public boolean hMSet(String key, Object object) {
        return redisTemplate.execute((RedisCallback<Boolean>) connection -> {
            RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
            Map<String, ?> map = BeanUtils.bean2Map(object);
            Map<byte[],byte[]> hashes = Maps.newHashMap();
            for(String mapKey:map.keySet()) {
                byte[] mapKeyBytes = serializer.serialize(mapKey);
                byte[] valueBytes = serializer.serialize(String.valueOf(map.get(mapKey)));
                hashes.put(mapKeyBytes,valueBytes);
            }
            connection.hMSet(serializer.serialize(key),hashes);
            return expire(key,7200);
        });
    }

    /**
     * 获取hash类型数据
     * @param key
     * @return map
     */
    public Map<String, String> hGetAll(String key) {
        return redisTemplate.execute((RedisCallback<Map<String,String>>) connection -> {
            RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
            Map<byte[],byte[]> map = connection.hGetAll(serializer.serialize(key));
            return map != null ? map.keySet().stream().collect(Collectors.toMap(serializer::deserialize, mapKey -> serializer.deserialize(map.get(mapKey)), (a, b) -> b)) :null;
        });
    }
}
