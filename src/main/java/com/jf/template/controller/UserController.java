package com.jf.template.controller;

import com.jf.template.aspect.log.MethodLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * 用户controller
 *
 * @author 江峰
 * @date 2020/8/1 15:09
 */
@RestController
@Slf4j
public class UserController {

    @GetMapping(value = "/user")
    @MethodLogger
    public String getUser() {
        return LocalDateTime.now().toString();
    }
}
