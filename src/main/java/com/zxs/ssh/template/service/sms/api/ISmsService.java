package com.zxs.ssh.template.service.sms.api;


import com.zxs.ssh.template.model.SmsContent;

/**
 * Project Name:electronic-invoice
 * File Name:ISmsService
 * Package Name:com.yk.invoice.service.manage.sms.api
 * Date:2019/1/22
 * Author:zengxueshan
 * Description:
 * Copyright (c) 2019, 重庆云凯科技有限公司 All Rights Reserved.
 */


public interface ISmsService {
    /**
     * 发送短信
     *
     * @param smsContent 短信内容
     */
    String sendSms(SmsContent smsContent) throws Exception;
}
