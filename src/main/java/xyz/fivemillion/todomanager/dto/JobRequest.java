package xyz.fivemillion.todomanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JobRequest {

    private String triggerId;
    private String jobName;
    private String cron;
}
