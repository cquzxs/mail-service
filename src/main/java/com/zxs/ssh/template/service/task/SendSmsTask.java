package com.zxs.ssh.template.service.task;

import com.zxs.ssh.template.model.SmsContent;
import com.zxs.ssh.template.model.queue.SmsQueue;
import com.zxs.ssh.template.service.sms.api.ISmsService;
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

@Component("sendSmsTask")
@EnableScheduling
public class SendSmsTask {

    @Resource(name = "smsService")
    private ISmsService smsService;

    private static final long batchCount = 10;

    private static final Logger logger = LoggerFactory.getLogger(SendSmsTask.class);

    /**
     * 出队
     */
    @Scheduled(fixedDelay = 10000)
    private void pull(){

        for (int i = 0; i < batchCount; i++) {
            try{
                SmsContent smsContent = SmsQueue.pull(5000);
                if(smsContent != null){
                    String rely = this.smsService.sendSms(smsContent);
                    logger.info("短信发送成功:"+smsContent.getTo()+"    "+rely);
                }
            }catch (Exception e){
                logger.info("短信发送异常",e.getMessage());
            }
        }
    }
}
