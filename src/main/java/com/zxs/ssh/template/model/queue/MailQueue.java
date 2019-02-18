package com.zxs.ssh.template.model.queue;

import com.zxs.ssh.template.model.MailContent;

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


public class MailQueue{

    private static LinkedBlockingQueue<MailContent> queue = new LinkedBlockingQueue<>();

    public static void push(MailContent o) throws Exception {
        queue.put(o);
    }

    public static MailContent pull(long timeout) throws Exception {
        return queue.poll(timeout, TimeUnit.MILLISECONDS);
    }
}
