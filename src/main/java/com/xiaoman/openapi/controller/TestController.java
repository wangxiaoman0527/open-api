package com.xiaoman.openapi.controller;


import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/wx")
@Slf4j
public class TestController {
    @Autowired
    private WxMaService wxMaService;

//    @Autowired
//    private UserService userService; // 你的用户服务

    @PostMapping("/getOpenid")
    public Object getOpenid(@RequestBody CodeRequest codeRequest) {
        Map<String, Object> result = new HashMap<>();
        try {
            // 使用 weixin-java-miniapp 库直接获取session信息
            WxMaJscode2SessionResult sessionInfo = wxMaService.getUserService()
                    .getSessionInfo(codeRequest.getCode());

            ApiResponse response = new ApiResponse();
            response.setCode(200);
            response.setMessage("获取成功");
            response.setData(sessionInfo);
            // 模拟返回测试数据
            result.put("code", 200);
            result.put("openid", sessionInfo.getOpenid());
            result.put("message", "获取成功");
            return result;

        } catch (Exception e) {

            return result;
        }
    }
    @PostMapping("/phone-login")
    public void phoneLogin(@RequestBody @Valid PhoneLoginRequest request) {
        try {
            log.info("收到手机号登录请求, loginCode前8位: {}, phoneCode长度: {}",
                    request.getLoginCode().substring(0, Math.min(8, request.getLoginCode().length())),
                    request.getPhoneCode().length());

            // 1. 获取 session_key 和 openid
            WxMaJscode2SessionResult sessionInfo = wxMaService.getUserService()
                    .getSessionInfo(request.getLoginCode());

            log.info("成功获取sessionInfo, openid: {}", sessionInfo.getOpenid());

            // 2. 获取手机号
            WxMaPhoneNumberInfo phoneNumberInfo = wxMaService.getUserService()
                    .getPhoneNoInfo(request.getPhoneCode());

            String phoneNumber = phoneNumberInfo.getPurePhoneNumber();
            log.info("成功获取手机号: {}", phoneNumber);

            // 3. 处理用户登录/注册
//            User user = userService.createOrUpdateUser(
//                    sessionInfo.getOpenid(),
//                    phoneNumber,
//                    sessionInfo.getSessionKey() // 可以缓存session_key用于后续解密
//            );

//            // 4. 生成JWT token
//            String token = JwtUtils.generateToken(user.getId(), phoneNumber);
//
//            // 5. 构建响应
//            UserInfo userInfo = new UserInfo();
//            userInfo.setUserId(user.getId());
//            userInfo.setPhoneNumber(phoneNumber); // 实际生产中建议脱敏
//            userInfo.setNickname(user.getNickname());
//            userInfo.setAvatarUrl(user.getAvatarUrl());
//
//            LoginResult loginResult = new LoginResult(token, userInfo);
//
//            return CommonResult.success(loginResult);

        } catch (Exception e) {
            log.error("手机号登录系统异常: ", e);
        }
    }

    @Data
    public static class CodeRequest {
        private String code;
    }

    @Data
    public static class ApiResponse {
        private int code;
        private String message;
        private Object data;
    }

}
