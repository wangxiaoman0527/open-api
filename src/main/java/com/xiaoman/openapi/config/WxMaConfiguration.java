package com.xiaoman.openapi.config;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.config.WxMaConfig;
import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WxMaConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "wx.miniapp.configs[0]")
    public WxMaConfig wxMaConfig() {
        return new WxMaDefaultConfigImpl();
    }

    @Bean
    public WxMaService wxMaService(WxMaConfig wxMaConfig) {
        WxMaService service = new WxMaServiceImpl();
        service.setWxMaConfig(wxMaConfig);
        return service;
    }
}
