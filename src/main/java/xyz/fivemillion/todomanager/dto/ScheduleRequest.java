package xyz.fivemillion.todomanager.dto;

import lombok.Data;
import xyz.fivemillion.todomanager.domain.Todo;
import xyz.fivemillion.todomanager.dto.todo.TodoRegisterRequest;
import xyz.fivemillion.todomanager.util.UuidUtils;

import java.util.HashMap;
import java.util.Map;

@Data
public class ScheduleRequest {

    private String jobId;
    private String triggerId;
    private String group;
    private String cronExp;
    private Map<String, Object> jobData = new HashMap<>();

    public static ScheduleRequest build(TodoRegisterRequest request, Todo todo) {
        ScheduleRequest scheduleRequest = new ScheduleRequest();
        scheduleRequest.setJobId(UuidUtils.random());
        scheduleRequest.setTriggerId(UuidUtils.random());
        scheduleRequest.setGroup(request.getUserId());
        scheduleRequest.setCronExp(request.getCron());
        scheduleRequest.getJobData().put("todo", todo);
        return scheduleRequest;
    }
}
