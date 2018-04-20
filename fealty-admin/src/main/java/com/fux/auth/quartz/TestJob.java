package com.fux.auth.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.io.Serializable;

/**
 * 实现序列化接口、防止重启应用出现quartz Couldn't retrieve job because a required class was not found 的问题
 * Created by fuxiaoj on 2018/04/16 11:46
 */
public class TestJob implements Job, Serializable{

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("大吉大利,今晚吃鸡！");
    }
}
