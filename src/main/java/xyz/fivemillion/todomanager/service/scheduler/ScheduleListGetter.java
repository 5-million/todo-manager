package xyz.fivemillion.todomanager.service.scheduler;

import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.matchers.GroupMatcher;
import xyz.fivemillion.todomanager.dto.ScheduleInfo;

import java.util.List;

public interface ScheduleListGetter {

    List<ScheduleInfo> getSchedulerList(Scheduler scheduler, GroupMatcher<JobKey> matcher) throws SchedulerException;
}
