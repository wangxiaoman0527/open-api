package com.xiaoman.openapi.controller;

import com.github.binarywang.wxpay.bean.notify.WxPayNotifyResponse;
import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.result.WxPayUnifiedOrderResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.util.SignUtils;
import com.xiaoman.openapi.dal.OrderRequest;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

// 支付控制器
@RestController
@RequestMapping("/wxpay")
public class WxPayController {

    @Resource
    private WxPayService wxPayService;


    @PostMapping("/createOrder")
    public Object createOrder(@RequestBody OrderRequest orderRequest) {
        try {
            WxPayUnifiedOrderRequest request = new WxPayUnifiedOrderRequest();
            request.setBody(orderRequest.getBody());
            request.setOutTradeNo(orderRequest.getOutTradeNo());
//            request.setTotalFee(orderRequest.getTotalFee());
            request.setTotalFee(1);
            request.setSpbillCreateIp(orderRequest.getSpbillCreateIp());
            request.setNotifyUrl("localhost:8080/wxpay/notify");
            request.setTradeType("JSAPI");
            request.setOpenid(orderRequest.getOpenid());
            request.setSpbillCreateIp("127.0.0.1");

            // 1. 调用统一下单接口获取prepay_id
            WxPayUnifiedOrderResult result = wxPayService.unifiedOrder(request);
            String prepayId = result.getPrepayId();

            // 2. 构建支付参数
            Map<String, String> payParams = new HashMap<>();
            payParams.put("appId", wxPayService.getConfig().getAppId());
            payParams.put("timeStamp", String.valueOf(System.currentTimeMillis() / 1000));
            payParams.put("nonceStr", generateNonceStr()); // 使用SDK的方法
            payParams.put("package", "prepay_id=" + prepayId);
            payParams.put("signType", "MD5");

            // 3. 使用SDK的createSign方法生成签名
            String sign = SignUtils.createSign(payParams, "MD5", wxPayService.getConfig().getMchKey(), new String[]{});
            payParams.put("paySign", sign);

            return ResponseEntity.ok(payParams);
        } catch (WxPayException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", "生成支付参数失败"));
        }
    }

    @PostMapping("/notify")
    public String payNotify(HttpServletRequest request) {
        try {
            // 方式1：使用字符串参数（更通用）
            String xmlData = getRequestBody(request);
            WxPayOrderNotifyResult notifyResult = wxPayService.parseOrderNotifyResult(xmlData);

            // 方式2：或者使用InputStream
//             WxPayOrderNotifyResult notifyResult = wxPayService.parseOrderNotifyResult(request.getInputStream());

            // 处理业务逻辑
            String outTradeNo = notifyResult.getOutTradeNo();
            String transactionId = notifyResult.getTransactionId();

            // 更新订单状态等业务操作
//            orderService.updateOrderStatus(outTradeNo, transactionId);

            return WxPayNotifyResponse.success("OK");
        } catch (WxPayException e) {
            return WxPayNotifyResponse.fail(e.getMessage());
        } catch (Exception e) {
            return WxPayNotifyResponse.fail("处理通知失败");
        }
    }

    // 从HttpServletRequest获取请求体内容
    private String getRequestBody(HttpServletRequest request) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        return stringBuilder.toString();
    }

    private String generateNonceStr() {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 32);
    }
}
