package com.zxs.ssh.template.controller;

import com.zxs.ssh.template.model.SmsContent;
import com.zxs.ssh.template.model.queue.SmsQueue;
import com.zxs.ssh.template.model.response.ResponseResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * Project Name:mail-service
 * File Name:SmsController
 * Package Name:com.zxs.ssh.template.controller
 * Date:2019/2/18
 * Author:zengxueshan
 * Description:
 * Copyright (c) 2019, 重庆云凯科技有限公司 All Rights Reserved.
 */

@RestController("smsController")
public class SmsController {
    /**
     * 发送短信
     *
     * @return 结果
     */
    @PostMapping("sms/send")
    public Object sendSms(@RequestBody SmsContent smsContent){
        try{
            checkSms(smsContent);
            SmsQueue.push(smsContent);
            return new ResponseResult(0, ResponseResult.ResponseState.SUCCESS, new HashMap<>(), "发送短信申请成功");
        }catch (Exception e){
            return new ResponseResult(1, ResponseResult.ResponseState.EXCEPTION, new HashMap<>(), e.getMessage());
        }
    }

    private void checkSms(SmsContent smsContent) throws Exception{
        if(smsContent == null){
            throw new Exception("参数不能为空");
        }
        if(smsContent.getContent() == null || smsContent.getContent().isEmpty()){
            throw new Exception("内容不能为空");
        }
        if(smsContent.getTo() == null || smsContent.getTo().isEmpty()){
            throw new Exception("收信人不能为空");
        }
    }
}
