package com.jf.template.client.hystrix;

import com.jf.template.client.FcsClient;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 调用蜂羽主项目，异常熔断处理
 *
 * @author 江峰
 * @date 2020/7/19 15:04
 */
@Component
@Slf4j
public class FcsClientFallback implements FallbackFactory<FcsClient> {
    @Override
    public FcsClient create(Throwable throwable) {
        return null;
    }

    @Override
    public FcsClient create(Throwable throwable) {
        log.error("调用FCS失败：", throwable);
        return new FcsClient() {
            @Override
            public BaseResult<List<CompanyDTO>> getCompanyListByLevel(CompanyRequestDTO companyRequestDTO) {
                ServiceException serviceException = FeignUtil.decodeFeignException("获取会员等级对应的所有货主公司", throwable);
                return BaseResult.createFailResult(serviceException.getMsg(), serviceException.getCode());
            }
        };
    }
}