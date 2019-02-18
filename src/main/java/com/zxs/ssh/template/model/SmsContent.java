package com.zxs.ssh.template.model;

/**
 * Project Name:electronic-invoice
 * File Name:SmsContent
 * Package Name:com.yk.invoice.model.api.query.notify
 * Date:2019/1/2
 * Author:zengxueshan
 * Description:构造短信
 * Copyright (c) 2019, 重庆云凯科技有限公司 All Rights Reserved.
 */


public class SmsContent {
    private String content; //短信内容
    private String to;  //收信方

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
