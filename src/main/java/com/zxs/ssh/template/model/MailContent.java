package com.zxs.ssh.template.model;

import java.util.List;

/**
 * Project Name:electronic-invoice
 * File Name:MailContent
 * Package Name:com.yk.invoice.model.api.query.notify
 * Date:2019/1/2
 * Author:zengxueshan
 * Description:构造邮件
 * Copyright (c) 2019, 重庆云凯科技有限公司 All Rights Reserved.
 */


public class MailContent {
    private String subject;  //邮件主题
    private String content; //邮件内容
    private List<String> attachments;  //附件内容
    private String to;  //收件方

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<String> attachments) {
        this.attachments = attachments;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
