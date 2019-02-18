package com.zxs.ssh.template.service.mail.impl;

import com.zxs.ssh.template.service.mail.api.IMailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.File;
import java.util.List;

/**
 * Project Name:electronic-invoice
 * File Name:MailServiceImpl
 * Package Name:com.yk.invoice.service.manage.mail.impl
 * Date:2018/12/29
 * Author:zengxueshan
 * Description:
 * Copyright (c) 2018, 重庆云凯科技有限公司 All Rights Reserved.
 */

@Service("mailService")
public class MailServiceImpl implements IMailService {

    @Resource(name = "mailSender")
    private JavaMailSenderImpl mailSender;

    @Value("${mail.smtp.from}")
    private String from;

    @Value("${mail.smtp.from.name}")
    private String fromName;

    /**
     * 发送邮件
     *
     * @param subject     邮件主题
     * @param content     邮件内容
     * @param attachments 附件列表
     * @param to          收件人
     */
    public void sendMail(String subject, String content, List<String> attachments, String to) throws Exception {
        System.getProperties().setProperty("mail.mime.splitlongparameters", "false");
        MimeMessage mailMessage = this.mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mailMessage, true, "utf-8");

        // 设置发件人信息
        helper.setFrom(this.from, this.fromName);
        // 设置收件人
        helper.setTo(to);
        // 设置主题
        helper.setSubject(subject);
        // 邮件内容
        helper.setText(content);

        // 添加附件
        if (attachments != null) {
            for (String attachment : attachments) {
                try {
                    File tempFile = new File(attachment);
                    String fileName = tempFile.getName();
                    helper.addAttachment(MimeUtility.encodeText(fileName), tempFile);
                } catch (Exception e) {
                    throw new Exception("邮件添加附件异常");
                }
            }
        }
        // 发送邮件
        this.mailSender.send(mailMessage);
    }
}
