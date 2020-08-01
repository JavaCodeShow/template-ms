package com.jf.template.aspect.log;

/**
 * 请求日志打印参数类型枚举
 *
 * @author 江峰
 * @date 2020/8/1 14:34
 */
public enum LogTypeEnum {
    REQUEST_PARAM,
    RESPONSE,
    FULL;
    private LogTypeEnum() {
    }
}
