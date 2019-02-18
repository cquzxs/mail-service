package com.zxs.ssh.template.service.sms.impl;

import com.zxs.ssh.template.model.SmsContent;
import com.zxs.ssh.template.service.sms.api.ISmsService;
import com.zxs.ssh.template.service.util.Encrypt;
import com.zxs.ssh.template.service.util.HttpClientHelper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Project Name:electronic-invoice
 * File Name:SmsServiceImpl
 * Package Name:com.yk.invoice.service.manage.sms.impl
 * Date:2019/1/22
 * Author:zengxueshan
 * Description:
 * Copyright (c) 2019, 重庆云凯科技有限公司 All Rights Reserved.
 */

@Service("smsService")
public class SmsServiceImpl implements ISmsService {

    @Value("${sms.server.url}")
    private String smsServerUrl;

    /**
     * 发送短信
     *
     * @param smsContent 短信内容
     */
    @Override
    public String sendSms(SmsContent smsContent) throws Exception{
        String temp = smsContent.getTo() +
                "|" + smsContent.getContent() +
                "|" + "电子发票平台" +
                "|" + "wxb2" +
                "|" + "False";
        Map<String, Object> map = new HashMap<>();
        map.put("Phones",smsContent.getTo());
        map.put("Content",smsContent.getContent());
        map.put("Title","电子发票平台");
        map.put("SmsAccountId","wxb2");
        map.put("RequestFrom",6);
        map.put("RequestScope","电子发票平台");
        map.put("IsPay","False");
        map.put("Sign", Encrypt.md5(temp).toUpperCase());
        HttpClientHelper h = new HttpClientHelper();
        return h.sendJsonHttpPost(smsServerUrl, new JSONObject(map).toString());
    }
}
