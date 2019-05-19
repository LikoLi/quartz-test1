package org.liko.project;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class RAMJob implements Job {

    private Logger logger = LoggerFactory.getLogger(RAMJob.class);

    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        Object oca = arg0.getJobDetail().getJobDataMap().get("OCA");
        System.out.println("OCA: " + oca);
        System.out.println(("hello world ! hello Quartz - "+new Date()));
    }

}
