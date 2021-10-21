package xyz.fivemillion.todomanager.service.scheduler;

import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import xyz.fivemillion.todomanager.domain.Todo;
import xyz.fivemillion.todomanager.dto.ScheduleInfo;
import xyz.fivemillion.todomanager.util.JobUtils;

import java.util.ArrayList;
import java.util.List;

public class TodoScheduleListGetter implements ScheduleListGetter {

    /**
     * morning, night 메시지 스케줄러 리스트를 뽑아올 때 todo가 존재하지 않기 때문에 todo의 getter에서 NullPointerException 발생
     * 해결 필요
     */

    @Override
    public List<ScheduleInfo> getSchedulerList(Scheduler scheduler, GroupMatcher<JobKey> matcher) throws SchedulerException {
        List<ScheduleInfo> list = new ArrayList<>();
        for (JobKey jobKey : scheduler.getJobKeys(matcher)) {
            JobDataMap jobDataMap = scheduler.getJobDetail(jobKey).getJobDataMap();
            Todo todo = JobUtils.getFromJobDataMap(jobDataMap, Todo.class, "todo");

            for (Trigger trigger : scheduler.getTriggersOfJob(jobKey)) {
                ScheduleInfo info = new ScheduleInfo();
                info.setJobId(jobKey.getName());
                info.setTriggerId(trigger.getKey().getName());
                info.setGroup(jobKey.getGroup());

                info.getJobData().put("todoId", todo.getId());
                info.getJobData().put("todo", todo.getTodo());
                info.getJobData().put("message", todo.getMessage());

                list.add(info);
            }
        }

        return list;
    }
}
