package com.jf.template.service.impl;

import com.jf.template.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * redis工具
 *
 * @Author 张云和
 * @Date 2018/4/10
 * @Time 16:54
 */
@Service
@Slf4j
public class RedisServiceImpl implements RedisService {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void set(final String key, final String value, long expire) {
        stringRedisTemplate.opsForValue().set(key, value, expire, TimeUnit.SECONDS);
    }

    @Override
    public String get(final String key) {

        return stringRedisTemplate.opsForValue().get(key);
    }

    @Override
    public boolean expire(final String key, long expire) {
        return redisTemplate.expire(key, expire, TimeUnit.SECONDS);
    }

    @Override
    public boolean exists(String key) {

        return stringRedisTemplate.hasKey(key);
    }

    @Override
    public void del(String key) {

        stringRedisTemplate.delete(key);
    }

    @Override
    public void del(Set<String> keys) {

        stringRedisTemplate.delete(keys);
    }

    @Override
    public void hSet(String key, String field, String value) {

        stringRedisTemplate.opsForHash().put(key, field, value);
    }

    @Override
    public void hMSet(String key, Map value) {

        stringRedisTemplate.opsForHash().putAll(key, value);
    }

    @Override
    public void sAdd(String key, String value) {

        stringRedisTemplate.opsForSet().add(key, value);
    }

    @Override
    public boolean sIsMember(String key, String value) {

        return stringRedisTemplate.opsForSet().isMember(key, value);
    }

    @Override
    public String hGet(String key, String field) {
        return null;
    }

    @Override
    public long incr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }
        return stringRedisTemplate.opsForValue().increment(key, delta);
    }

    @Override
    public Boolean setExpire(String key, long second) {
        return stringRedisTemplate.expire(key, second, TimeUnit.SECONDS);
    }

    /**
     * 根据key 获取过期时间
     *
     * @param key 键 不能为null
     * @return 时间(秒) 返回0代表为永久有效
     */
    @Override
    public long getExpire(String key) {
        return stringRedisTemplate.getExpire(key);
    }

    @Override
    public boolean tryLock(int expire, String lockKey, String keyValue) {

        Boolean result = stringRedisTemplate.opsForValue().setIfAbsent(lockKey, keyValue, expire, TimeUnit.SECONDS);
        log.info("get lock = {}", result);
        if (null != result && result) {
            return true;
        }
        // 如果锁超时 ***
        String currentValue = stringRedisTemplate.opsForValue().get(lockKey);
        if (!StringUtils.isEmpty(currentValue) && Long.parseLong(currentValue) < System.currentTimeMillis()) {
            // key删除
            stringRedisTemplate.delete(lockKey);
            // 再次获取
            Boolean res = stringRedisTemplate.opsForValue().setIfAbsent(lockKey, keyValue, expire, TimeUnit.SECONDS);
            return null != res && res;
        }
        return false;
    }

    @Override
    public void unLock(String lockKey, String keyValue) {

        String value = stringRedisTemplate.opsForValue().get(lockKey);
        if (keyValue.equals(value)) {
            // 当redis里面存储的值相等时，才会进行删除
            stringRedisTemplate.delete(lockKey);
        }
    }

    @Override
    public void setIfAbsent(String lockKey, String keyValue, long expire) {

        stringRedisTemplate.opsForValue().setIfAbsent(lockKey, keyValue, expire, TimeUnit.SECONDS);
    }

}
