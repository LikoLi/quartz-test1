package org.liko.project;

import java.util.Date;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

public class RAMQuartzTest {

    private static Logger logger = LoggerFactory.getLogger(RAMJob.class);

    public static void main(String[] args) throws SchedulerException {
        //1.创建Scheduler的工厂
        SchedulerFactory sf = new StdSchedulerFactory();

        //2.从工厂中获取调度器实例
        Scheduler scheduler = sf.getScheduler();

        //3.创建JobDetail
        JobDetail jb = JobBuilder.newJob(RAMJob.class)
                .withDescription("this is hello job")//job的描述
                .withIdentity("helloJob", "helloGroup")//job 的name和group
                .build();
        jb.getJobDataMap().put("OCA", "LQ OCA");

        //任务运行的时间，SimpleSchedle类型触发器有效
        long time=  System.currentTimeMillis() + 3*1000L; //3秒后启动任务
        Date statTime = new Date(time);
        //4.创建Trigger
        //使用SimpleScheduleBuilder或者CronScheduleBuilder
        Trigger trigger = TriggerBuilder.newTrigger()
                .withDescription("")
                .withIdentity("helloTrigger", "helloTriggerGroup")
                .startAt(statTime)//默认当前时间启动
                .withSchedule(simpleSchedule()
                        .withIntervalInSeconds(5)
                        .repeatForever())//5秒执行一次
                .build();

        //5.注册任务和定时器
        scheduler.scheduleJob(jb, trigger);

        //6.启动 调度器
        scheduler.start();
        System.out.println(("启动时间 ："+new Date()));
    }
}
