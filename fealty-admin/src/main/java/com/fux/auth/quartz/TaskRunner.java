package com.fux.auth.quartz;

import com.fux.auth.entity.AuthJob;
import com.fux.auth.service.AuthJobService;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 初始化定时任务
 * Created by fuxiaoj on 2018/04/16 13:45
 */
@Component
public class TaskRunner implements ApplicationRunner {

    @Resource
    private AuthJobService authJobService;

    @Resource
    private Scheduler scheduler;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        List<AuthJob> list = authJobService.list();
        for (AuthJob authJob : list) {
            Class cls = Class.forName(authJob.getClassName()) ;
            cls.newInstance();
            //构建job信息
            JobDetail job = JobBuilder.newJob(cls).withIdentity(authJob.getJobName(), authJob.getJobGroup()) .withDescription(authJob.getDescription()).build();
            // 触发时间点
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(authJob.getCron());
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger"+authJob.getJobName(), authJob.getJobGroup()).startNow().withSchedule(cronScheduleBuilder).build();
            scheduler.scheduleJob(job, trigger);
        }
    }
}
