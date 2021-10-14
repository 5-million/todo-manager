package xyz.fivemillion.todomanager.service.scheduler;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.stereotype.Service;
import xyz.fivemillion.todomanager.dto.ScheduleInfo;
import xyz.fivemillion.todomanager.dto.ScheduleRequest;
import xyz.fivemillion.todomanager.job.SendMessageJob;

import java.util.List;

@Service
public class TodoScheduleService extends AbstractScheduleService implements ScheduleService {

    public TodoScheduleService(Scheduler scheduler) {
        super(scheduler, new TodoScheduleListGetter());
    }

    @Override
    public void register(ScheduleRequest request) throws SchedulerException {
        setScheduleJob(
                buildJobDetail(SendMessageJob.class, request.getJobId(), request.getGroup(), request.getJobData()),
                buildTrigger(request.getTriggerId(), request.getGroup(), request.getCronExp())
        );
    }

    @Override
    public void modify() {

    }

    @Override
    public void delete() {

    }

    @Override
    public void getScheduler() {

    }

    @Override
    public List<ScheduleInfo> getSchedulerList(String group) throws SchedulerException {
        return super.getSchedulerList(group);
    }
}
