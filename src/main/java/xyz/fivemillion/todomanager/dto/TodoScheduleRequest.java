package xyz.fivemillion.todomanager.dto;

import lombok.Data;
import xyz.fivemillion.todomanager.domain.Todo;
import xyz.fivemillion.todomanager.util.UuidUtils;

import java.util.HashMap;
import java.util.Map;

@Data
public class TodoScheduleRequest {

    private String jobId;
    private String triggerId;
    private String group;
    private String cronExp;
    private Map<String, Object> jobData;

    public TodoScheduleRequest() {
        this.jobId = UuidUtils.random();
        this.triggerId = UuidUtils.random();
        this.jobData = new HashMap<>();
    }

    public static TodoScheduleRequest build(Todo todo) {
        TodoScheduleRequest todoScheduleRequest = new TodoScheduleRequest();
        todoScheduleRequest.setJobId(UuidUtils.random());
        todoScheduleRequest.setTriggerId(UuidUtils.random());
        todoScheduleRequest.setGroup(todo.getUser().getUserId());
        todoScheduleRequest.setCronExp(todo.getCron());
        todoScheduleRequest.getJobData().put("todo", todo);
        return todoScheduleRequest;
    }
}
