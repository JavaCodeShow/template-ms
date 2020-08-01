package com.jf.template.client;

import com.jf.template.client.hystrix.FcsClientFallback;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * fcs蜂羽主项目服务
 *
 * @author 江峰
 * @date 2020/7/19 15:02
 */
@FeignClient(name = "fy-core", fallbackFactory = FcsClientFallback.class)
public interface FcsClient {

//     /**
//      * 根据最大id,以及size获取会员等级对应的所有货主公司
//      *
//      * @param companyRequestDTO
//      * @return
//      */
//     @PostMapping(value = "/company/vip/level/list", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//     BaseResult<List<CompanyDTO>> getCompanyListByLevel(@RequestBody CompanyRequestDTO companyRequestDTO);
}
