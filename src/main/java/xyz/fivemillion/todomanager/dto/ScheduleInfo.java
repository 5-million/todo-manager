package xyz.fivemillion.todomanager.dto;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ScheduleInfo {

    private String jobId;
    private String triggerId;
    private String group;
    private String cronExp;
    private Map<String, Object> jobData = new HashMap<>();
}
