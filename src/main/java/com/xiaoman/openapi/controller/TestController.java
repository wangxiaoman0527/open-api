package com.xiaoman.openapi.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.catalina.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/test")
@Tag(name = "API网关", description = "统一API调用入口")
public class TestController {

    @GetMapping("/{userId}")
    public User getUser(@PathVariable Long userId) {
        // 实现代码
        return null;
    }
}
