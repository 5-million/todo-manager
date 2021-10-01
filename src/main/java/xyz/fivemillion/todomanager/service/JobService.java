package xyz.fivemillion.todomanager.service;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;
import xyz.fivemillion.todomanager.domain.Todo;
import xyz.fivemillion.todomanager.dto.JobRequest;
import xyz.fivemillion.todomanager.job.SendMessageJob;
import xyz.fivemillion.todomanager.repository.TodoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class JobService {

    private JobService(TodoRepository todoRepository, SchedulerFactoryBean scheduler) throws SchedulerException {
        // 애플리케이션 실행시 등록된 todo가 있다면 스케줄에 추가
        Optional<List<Todo>> opt = todoRepository.findAll();
        if (opt.isPresent()) {
            List<Todo> todos = opt.get();
            for (Todo todo : todos) {
                registerJob(scheduler.getScheduler(), todo);
            }
        }
    }

    public boolean registerJob(Scheduler scheduler, Todo todo) {
        boolean result = false;

        try {
            JobDataMap jobDataMap = createJobDataMap(todo);
            JobDetail jobDetail =
                    JobBuilder
                            .newJob(SendMessageJob.class)
                            .withIdentity("job" + todo.getId())
                            .setJobData(jobDataMap).build();
            result = setJobSchedule(scheduler, jobDetail, todo);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return result;
    }

    private JobDataMap createJobDataMap(Todo todo) {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("todo", todo);

        return jobDataMap;
    }

    private boolean setJobSchedule(Scheduler scheduler, JobDetail jobDetail, Todo todo) {
        try {
            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("job" + todo.getId())
                    .withSchedule(CronScheduleBuilder.cronSchedule(todo.getCron()))
                    .build();
            scheduler.scheduleJob(jobDetail, trigger);
            scheduler.start();
        } catch (SchedulerException e) {
            log.error("Schedule 등록 실패");
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public List<JobRequest> getJobList(Scheduler scheduler) {
        List<JobRequest> result = new ArrayList<>();

        try {
            for (JobKey jobKey : scheduler.getJobKeys(null)) {
                scheduler.getTriggersOfJob(jobKey).stream().forEach(trigger -> {
                    JobRequest request =
                            new JobRequest(((Trigger) trigger).getKey().getName(), jobKey.getName(), "");
                    result.add(request);
                });
            }
        } catch (SchedulerException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }

        return result;
    }
}
