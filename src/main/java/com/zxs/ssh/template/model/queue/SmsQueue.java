package com.zxs.ssh.template.model.queue;

import com.zxs.ssh.template.model.MailContent;
import com.zxs.ssh.template.model.SmsContent;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Project Name:mail-service
 * File Name:MailQueue
 * Package Name:com.zxs.ssh.template.model.queue.impl
 * Date:2019/2/18
 * Author:zengxueshan
 * Description:
 * Copyright (c) 2019, 重庆云凯科技有限公司 All Rights Reserved.
 */


public class SmsQueue {

    private static LinkedBlockingQueue<SmsContent> queue = new LinkedBlockingQueue<>();

    public static void push(SmsContent o) throws Exception {
        queue.put(o);
    }

    public static SmsContent pull(long timeout) throws Exception {
        return queue.poll(timeout, TimeUnit.MILLISECONDS);
    }
}
