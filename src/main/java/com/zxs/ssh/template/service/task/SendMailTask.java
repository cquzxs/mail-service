package com.zxs.ssh.template.service.task;

import com.zxs.ssh.template.model.MailContent;
import com.zxs.ssh.template.service.mail.api.IMailService;
import com.zxs.ssh.template.model.queue.MailQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Project Name:mail-service
 * File Name:SendMailTask
 * Package Name:com.zxs.ssh.template.service.task
 * Date:2019/2/18
 * Author:zengxueshan
 * Description:
 * Copyright (c) 2019, 重庆云凯科技有限公司 All Rights Reserved.
 */

@Component("sendMailTask")
@EnableScheduling
public class SendMailTask {

    @Resource(name = "mailService")
    private IMailService mailService;

    private static final long batchCount = 10;

    private static final Logger logger = LoggerFactory.getLogger(SendMailTask.class);

    /**
     * 出队
     */
    @Scheduled(fixedDelay = 10000)
    private void pull(){

        for (int i = 0; i < batchCount; i++) {
            try{
                MailContent mailContent = MailQueue.pull(5000);
                if(mailContent != null){
                    this.mailService.sendMail(mailContent.getSubject(),mailContent.getContent(),mailContent.getAttachments(),mailContent.getTo());
                    logger.info("邮件发送成功:"+mailContent.getTo());
                }
            }catch (Exception e){
                logger.info("邮件发送异常",e.getMessage());
            }
        }
    }
}
