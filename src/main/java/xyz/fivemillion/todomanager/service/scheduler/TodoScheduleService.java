package xyz.fivemillion.todomanager.service.scheduler;

import org.quartz.Job;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import xyz.fivemillion.todomanager.dto.RescheduleRequest;
import xyz.fivemillion.todomanager.dto.ScheduleInfo;
import xyz.fivemillion.todomanager.dto.TodoScheduleRequest;

import java.util.List;

public class TodoScheduleService extends AbstractScheduleService implements ScheduleService {

    public TodoScheduleService(Scheduler scheduler) {
        super(scheduler, new TodoScheduleListGetter());
    }

    @Override
    public void register(Class<? extends Job> job, TodoScheduleRequest request) throws SchedulerException {
        setScheduleJob(
                buildJobDetail(job, request.getJobId(), request.getGroup(), request.getJobData()),
                buildTrigger(request.getTriggerId(), request.getGroup(), request.getCronExp())
        );
    }

    @Override
    public void modify(RescheduleRequest request) throws SchedulerException {
        reschedule(request.getTriggerName(), request.getGroup(), request.getNewCronExp());
    }

    @Override
    public void delete(String jobId, String group) throws SchedulerException {
        deleteSchedule(jobId, group);
    }

    @Override
    public List<ScheduleInfo> getSchedulerList(String group) throws SchedulerException {
        return super.getSchedulerList(group);
    }
}
