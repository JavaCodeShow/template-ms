package com.jf.template.lock;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.jf.template.meta.constant.CommonConstant;

/**
 * @author luxinghui
 * @date 2019-05-23
 */
@Component
public class LockKeyGenerator implements CacheKeyGenerator {

    @Override
    public String getLockKey(ProceedingJoinPoint pjp) {

        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        CacheLock lockAnnotation = method.getAnnotation(CacheLock.class);
        // 同一个人同一个方法
        return CommonConstant.SYSTEM_CODE + lockAnnotation.delimiter() + "用户code"
                + "-" + "用户类型" + lockAnnotation.delimiter() + method.getName();
    }
}
