package com.xiaoman.openapi.dal;

import lombok.Data;

@Data
public class OrderRequest {
    // 商品描述
    private String body;

    // 商户订单号
    private String outTradeNo;

    // 订单金额（单位：分）
    private Integer totalFee;

    // 终端IP
    private String spbillCreateIp;

    // 支付类型（默认JSAPI）
    private String tradeType = "JSAPI";

    // 用户openid
    private String openid;

    // 通知地址
    private String notifyUrl;
}
