package com.xiaoman.openapi.config;

import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WxPayConfiguration {

    @Bean
    public WxPayConfig wxPayConfig() {
        WxPayConfig payConfig = new WxPayConfig();
        payConfig.setAppId("wx2a8ff037f03f483a");
        payConfig.setMchId("1500000000");
        payConfig.setMchKey("26a30b3de14b709e5359d3d7287ffcec");
        payConfig.setUseSandboxEnv(true);
        payConfig.setKeyPath("apiclient_cert.p12"); // 证书路径（如果需要）
        payConfig.setNotifyUrl("127.0.0.1：8080/wxpay/notify");
        return payConfig;
    }

    @Bean
    public WxPayService wxPayService(WxPayConfig wxPayConfig) {
        WxPayService wxPayService = new WxPayServiceImpl();
        wxPayService.setConfig(wxPayConfig);
        return wxPayService;
    }
}
