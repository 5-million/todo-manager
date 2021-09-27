package xyz.fivemillion.todomanager.controller;

import lombok.RequiredArgsConstructor;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.web.bind.annotation.*;
import xyz.fivemillion.todomanager.domain.Todo;
import xyz.fivemillion.todomanager.dto.JobRequest;
import xyz.fivemillion.todomanager.service.JobService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MessageController {

    private final JobService jobService;
    private final SchedulerFactoryBean scheduler;

    @PostMapping("/api/v1/slack/todo")
    private String registerJob(@RequestBody Todo todo) throws SchedulerException {
        if (jobService.registerJob(scheduler.getScheduler(), todo))
            return "SUCCESS";
        else return "FALSE";
    }


    @GetMapping("/api/v1/slack/todos")
    private List<JobRequest> getTodoList() throws SchedulerException {
        return jobService.getJobList(scheduler.getScheduler());
    }
}
