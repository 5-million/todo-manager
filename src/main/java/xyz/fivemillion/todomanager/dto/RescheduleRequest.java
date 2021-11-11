package xyz.fivemillion.todomanager.dto;

import lombok.Data;

@Data
public class RescheduleRequest {

    private Long todoId;
    private String triggerName;
    private String group;
    private String newCronExp;
}
