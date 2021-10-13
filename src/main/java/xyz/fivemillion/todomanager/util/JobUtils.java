package xyz.fivemillion.todomanager.util;

import org.quartz.JobDataMap;

public class JobUtils {

    public static <T> T getFromJobDataMap(JobDataMap jobDataMap, Class<T> type, String key) {
        return type.cast(jobDataMap.get(key));
    }
}
