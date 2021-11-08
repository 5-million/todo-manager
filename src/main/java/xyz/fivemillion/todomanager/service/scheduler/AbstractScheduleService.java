package xyz.fivemillion.todomanager.service.scheduler;

import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import xyz.fivemillion.todomanager.dto.ScheduleInfo;

import java.util.List;
import java.util.Map;

public class AbstractScheduleService {

    private Scheduler scheduler;
    private ScheduleListGetter getter;

    public AbstractScheduleService(Scheduler scheduler, ScheduleListGetter getter) {
        this.scheduler = scheduler;
        this.getter = getter;
    }

    protected void setScheduleJob(JobDetail jobDetail, Trigger trigger) throws SchedulerException {
        scheduler.scheduleJob(jobDetail, trigger);
    }

    protected JobDetail buildJobDetail(Class<? extends Job> job, String jobId, String group, Map<String, Object> data) {
        return JobBuilder.newJob(job)
                .withIdentity(jobId, group)
                .setJobData(buildJobDataMap(data))
                .build();
    }

    protected Trigger buildTrigger(String triggerId, String group, String cronExp) {
        return TriggerBuilder.newTrigger()
                .withIdentity(triggerId, group)
                .withSchedule(CronScheduleBuilder.cronSchedule(cronExp))
                .build();
    }

    protected JobDataMap buildJobDataMap(Map<String, Object> data) {
        if (data.isEmpty()) return null;

        JobDataMap jobDataMap = new JobDataMap();
        for (String key : data.keySet()) {
            jobDataMap.put(key, data.get(key));
        }

        return jobDataMap;
    }

    protected void reschedule(String triggerName, String group, String newCronExp) throws SchedulerException {
        TriggerKey triggerKey = new TriggerKey(triggerName, group);
        Trigger newTrigger = TriggerBuilder.newTrigger()
                .withIdentity(triggerKey)
                .withSchedule(CronScheduleBuilder.cronSchedule(newCronExp))
                .build();

        scheduler.rescheduleJob(triggerKey, newTrigger);
    }

    protected List<ScheduleInfo> getSchedulerList(String group) throws SchedulerException {
        return getter.getSchedulerList(scheduler, GroupMatcher.jobGroupEquals(group));
    }

    protected void deleteSchedule(String jobId, String group) throws SchedulerException {
        JobKey jobKey = new JobKey(jobId, group);
        scheduler.deleteJob(jobKey);
    }
}
