package com.zxs.ssh.template.service.mail.api;

import java.util.List;

/**
 * Project Name:electronic-invoice
 * File Name:IMailService
 * Package Name:com.yk.invoice.service.manage.mail.api
 * Date:2018/12/29
 * Author:zengxueshan
 * Description:
 * Copyright (c) 2018, 重庆云凯科技有限公司 All Rights Reserved.
 */


public interface IMailService {
    /**
     * 发送邮件
     *
     * @param subject     邮件主题
     * @param content     邮件内容
     * @param attachments 附件列表
     * @param to          收件人
     */
    void sendMail(String subject, String content, List<String> attachments, String to) throws Exception;
}
