package xyz.fivemillion.todomanager.dto.todo;

import lombok.Data;

@Data
public class TodoRegisterRequest {
    private String todo;
    private String message;
    private String cron;
    private String userId;
}
