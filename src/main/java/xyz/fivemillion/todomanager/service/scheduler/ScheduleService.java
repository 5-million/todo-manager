package xyz.fivemillion.todomanager.service.scheduler;

import org.quartz.Job;
import org.quartz.SchedulerException;
import xyz.fivemillion.todomanager.dto.RescheduleRequest;
import xyz.fivemillion.todomanager.dto.ScheduleInfo;
import xyz.fivemillion.todomanager.dto.TodoScheduleRequest;

import java.util.List;

public interface ScheduleService {

    void register(Class<? extends Job> job, TodoScheduleRequest request) throws SchedulerException;
    void modify(RescheduleRequest request) throws SchedulerException;
    void delete(String jobId, String group) throws SchedulerException;
    List<ScheduleInfo> getSchedulerList(String group) throws SchedulerException;
}
