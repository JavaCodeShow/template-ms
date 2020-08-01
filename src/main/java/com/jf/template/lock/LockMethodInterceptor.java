package com.jf.template.lock;

import com.jf.template.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;

/**
 * @author luxinghui
 * @date 2019-05-23
 */
@Aspect
@Slf4j
@Component
public class LockMethodInterceptor {

    @Resource
    private CacheKeyGenerator cacheKeyGenerator;

    @Resource
    private RedisService redisService;

    @Resource
    private RedissonClient redissonClient;

    @Around("execution(public * *(..)) && @annotation(com.jumstc.fengyu.css.lock.CacheLock)")
    public Object interceptor(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();

        CacheLock lock = method.getAnnotation(CacheLock.class);

        final String lockKey = cacheKeyGenerator.getLockKey(pjp);
        log.info("redis lock key is {}", lockKey);
        String expireTimeStr = String.valueOf(System.currentTimeMillis() + (lock.expire() * 1000));
        boolean remove = true;
        try {
            // 采用原生 API 来实现分布式锁
            final boolean success = remove = redisService.tryLock(lock.expire(), lockKey, expireTimeStr);

            if (!success) {
                // 重复提交异常不删除key
                // throw new ServiceException(ErrorCodeEnum.RESUBMIT.getErrorCode(),
                //     ErrorCodeEnum.RESUBMIT.getErrorMessage());
                throw new Exception("重复提交异常不删除key");
            }
            return pjp.proceed();

        } finally {
            if (remove) {
                redisService.unLock(lockKey, expireTimeStr);
                log.info("un lock = {}", lockKey);
            }
        }
    }
}
