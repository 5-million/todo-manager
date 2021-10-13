package xyz.fivemillion.todomanager.dto;

import lombok.Builder;
import lombok.Data;
import xyz.fivemillion.todomanager.dto.todo.TodoInfo;

@Data
@Builder
public class JobInfo {

    private String userId;
    private String jobName;
    private String triggerName;
    private TodoInfo todoInfo;
}
