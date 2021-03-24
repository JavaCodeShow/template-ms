package com.jf.template.lock;

import java.lang.annotation.*;

import com.jf.template.meta.constant.CommonConstant;

/**
 * 防重复提交
 *
 * @author zhangyunhe
 * @date 2020/6/19 11:47 上午
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface CacheLock {
    /**
     * redis 锁key的前缀
     *
     * @return redis 锁key的前缀
     */
    String prefix() default CommonConstant.SYSTEM_CODE;

    /**
     * 过期秒数,默认为5秒
     *
     * @return 轮询锁的时间
     */
    int expire() default 5;

    /**
     * <p>
     * Key的分隔符（默认 :）
     * </p>
     * <p>
     * 生成的Key：N:SO1008:500
     * </p>
     *
     * @return String
     */
    String delimiter() default ":";
}
