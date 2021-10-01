package xyz.fivemillion.todomanager.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import xyz.fivemillion.todomanager.domain.Todo;
import xyz.fivemillion.todomanager.dto.todo.TodoRegisterRequest;
import xyz.fivemillion.todomanager.service.JobService;
import xyz.fivemillion.todomanager.service.TodoService;

@RestController
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;
    private final JobService jobService;
    private final SchedulerFactoryBean scheduler;

    @PostMapping("/api/v1/todo")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody TodoRegisterRequest request) {
        Todo todo = todoService.register(request);
        jobService.registerJob(scheduler.getScheduler(), todo);
    }
}
