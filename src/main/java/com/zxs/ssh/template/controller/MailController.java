package com.zxs.ssh.template.controller;

import com.zxs.ssh.template.model.MailContent;
import com.zxs.ssh.template.model.response.ResponseResult;
import com.zxs.ssh.template.model.queue.MailQueue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * Project Name:mail-service
 * File Name:MailController
 * Package Name:com.zxs.ssh.template.controller
 * Date:2019/2/18
 * Author:zengxueshan
 * Description:
 * Copyright (c) 2019, 重庆云凯科技有限公司 All Rights Reserved.
 */

@RestController("mailController")
public class MailController {

    /**
     * 发送邮件
     *
     * @return 结果
     */
    @PostMapping("mail/send")
    public Object sendMail(@RequestBody MailContent mailContent){
        try{
            checkMail(mailContent);
            MailQueue.push(mailContent);
            return new ResponseResult(0, ResponseResult.ResponseState.SUCCESS, new HashMap<>(), "发送邮件申请成功");
        }catch (Exception e){
            return new ResponseResult(1, ResponseResult.ResponseState.EXCEPTION, new HashMap<>(), e.getMessage());
        }
    }

    private void checkMail(MailContent mailContent) throws Exception{
        if(mailContent == null){
            throw new Exception("邮件参数不能为空");
        }
        if(mailContent.getSubject() == null || mailContent.getSubject().isEmpty()){
            throw new Exception("主题不能为空");
        }
        if(mailContent.getContent() == null || mailContent.getContent().isEmpty()){
            throw new Exception("内容不能为空");
        }
        if(mailContent.getTo() == null || mailContent.getTo().isEmpty()){
            throw new Exception("收件人不能为空");
        }
    }
}
