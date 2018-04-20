package com.fux.auth.entity;

import javax.persistence.*;

/**
 * Created by fuxiaoj on 2018/04/16 10:58
 */
@Entity
@Table(name="auth_job")
public class AuthJob {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="job_name")
    private String jobName;//任务名称
    @Column(name="job_group")
    private String jobGroup;//任务分组
    private String description;//任务描述
    @Column(name="class_name")
    private String className;//执行类
    private String cron;//执行时间
    @Column(name="tigger_time")
    private String tiggerTime; //执行时间
    private String state;//任务状态

    public AuthJob() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public String getTiggerTime() {
        return tiggerTime;
    }

    public void setTiggerTime(String tiggerTime) {
        this.tiggerTime = tiggerTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
