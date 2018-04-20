package com.fux.auth.service.impl;

import com.fux.auth.dao.AuthJobDao;
import com.fux.auth.entity.AuthJob;
import com.fux.auth.entity.AuthRole;
import com.fux.auth.service.AuthJobService;
import com.fux.auth.util.TableSplitResult;
import com.fux.auth.vo.ResultVo;
import com.fux.auth.vo.SearchVo;
import org.quartz.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by fuxiaoj on 2018/04/16 10:45
 */
@Service
public class AuthJobServiceImpl implements AuthJobService {

    @Resource
    private AuthJobDao authJobDao;

    @Resource
    private Scheduler scheduler;

    @Override
    public TableSplitResult list(SearchVo search) {
        Pageable pageable = new PageRequest(search.getPageNumber(), search.getPageSize());
        return new TableSplitResult(authJobDao.findAll(pageable));
    }

    @Override
    public List<AuthJob> list() {
        return authJobDao.findAll();
    }

    @Override
    public AuthJob findById(Long id) {
        return authJobDao.findById(id).get();
    }

    @Override
    public ResultVo saveOrUpdate(AuthJob job) {
        try {
            //先判断任务是否已经存在了,删除原有任务
            if(job.getId() != null) {
                JobKey key = new JobKey(job.getJobName(), job.getJobGroup());
                scheduler.deleteJob(key);
            }
            //保存或修改数据
            authJobDao.save(job);
            //构建job信息
            Class cl = Class.forName(job.getClassName());
            cl.newInstance();
            JobDetail jobDetail = JobBuilder.newJob(cl).withIdentity(job.getJobName(), job.getJobGroup()).withDescription(job.getDescription()).build();
            //触发时间点
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCron());
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger"+job.getJobName(), job.getJobGroup()).startNow().withSchedule(cronScheduleBuilder).build();
            scheduler.scheduleJob(jobDetail, trigger);
            return ResultVo.successInfo("操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVo.failedInfo("添加或修改任务异常");
        }
    }

    @Override
    public ResultVo trigger(Long id) {
        try {
            AuthJob job = authJobDao.findById(id).get();
            JobKey key = new JobKey(job.getJobName(), job.getJobGroup());
            scheduler.triggerJob(key);
            return ResultVo.successInfo("触发任务成功");
        } catch (SchedulerException e) {
            e.printStackTrace();
            return ResultVo.failedInfo("触发任务异常");
        }
    }

    @Override
    public ResultVo pause(Long id) {
        try {
            AuthJob job = authJobDao.findById(id).get();
            JobKey key = new JobKey(job.getJobName(), job.getJobGroup());
            scheduler.pauseJob(key);
            return ResultVo.successInfo("停止任务成功");
        } catch (SchedulerException e) {
            e.printStackTrace();
            return ResultVo.failedInfo("停止任务异常");
        }
    }

    @Override
    public ResultVo resume(Long id) {
        try {
            AuthJob job = authJobDao.findById(id).get();
            JobKey key = new JobKey(job.getJobName(), job.getJobGroup());
            scheduler.resumeJob(key);
            return ResultVo.successInfo("恢复任务成功");
        } catch (SchedulerException e) {
            e.printStackTrace();
            return ResultVo.failedInfo("恢复任务异常");
        }
    }

    @Override
    public ResultVo delete(Long id) {
        try {
            AuthJob job = authJobDao.findById(id).get();
            JobKey key = new JobKey(job.getJobName(), job.getJobGroup());
            scheduler.deleteJob(key);
            //删除数据
            authJobDao.delete(job);
            return ResultVo.successInfo("删除任务成功");
        } catch (SchedulerException e) {
            e.printStackTrace();
            return ResultVo.failedInfo("删除任务异常");
        }
    }
}
