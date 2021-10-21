package xyz.fivemillion.todomanager.controller;

import lombok.RequiredArgsConstructor;
import org.quartz.SchedulerException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import xyz.fivemillion.todomanager.domain.Todo;
import xyz.fivemillion.todomanager.dto.*;
import xyz.fivemillion.todomanager.dto.todo.TodoRegisterRequest;
import xyz.fivemillion.todomanager.job.MsgSenderJob;
import xyz.fivemillion.todomanager.service.scheduler.ScheduleService;
import xyz.fivemillion.todomanager.service.TodoService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;
    private final ScheduleService scheduleService;

    @PostMapping("/api/v1/todo")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody TodoRegisterRequest request) throws SchedulerException {
        Todo todo = todoService.register(request);
        scheduleService.register(MsgSenderJob.class, TodoScheduleRequest.build(todo));
    }

    @GetMapping("/api/v1/todo")
    public Response getTodoList(@RequestParam("userid") String userId) throws SchedulerException {
        List<ScheduleInfo> schedulerList = scheduleService.getSchedulerList(userId);
        return new Response(schedulerList);
    }

    @PatchMapping("/api/v1/todo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void reschedule(@RequestBody RescheduleRequest request) throws SchedulerException {
        scheduleService.modify(request);
    }

    @DeleteMapping("/api/v1/todo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestParam("jobid") String jobId, @RequestParam("group") String group)
            throws SchedulerException {
        scheduleService.delete(jobId, group);
    }
}
