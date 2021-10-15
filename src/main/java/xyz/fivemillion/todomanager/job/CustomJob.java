package xyz.fivemillion.todomanager.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.SchedulerException;
import org.springframework.context.ApplicationContext;

public abstract class CustomJob implements Job {

    protected ApplicationContext getApplicationContext(JobExecutionContext context) throws SchedulerException {
        return (ApplicationContext) context.getScheduler().getContext().get("applicationContext");
    }
}
