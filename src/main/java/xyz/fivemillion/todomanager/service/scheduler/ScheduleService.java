package xyz.fivemillion.todomanager.service.scheduler;

import org.quartz.SchedulerException;
import xyz.fivemillion.todomanager.dto.ScheduleInfo;
import xyz.fivemillion.todomanager.dto.ScheduleRequest;

import java.util.List;

public interface ScheduleService {

    void register(ScheduleRequest request) throws SchedulerException;
    void modify();
    void delete();
    void getScheduler();
    List<ScheduleInfo> getSchedulerList(String group) throws SchedulerException;
}
