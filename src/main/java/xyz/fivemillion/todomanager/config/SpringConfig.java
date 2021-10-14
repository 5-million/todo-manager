package xyz.fivemillion.todomanager.config;

import lombok.RequiredArgsConstructor;
import org.quartz.Scheduler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xyz.fivemillion.todomanager.service.scheduler.ScheduleService;
import xyz.fivemillion.todomanager.service.scheduler.TodoScheduleService;

@Configuration
@RequiredArgsConstructor
public class SpringConfig {

    private final Scheduler scheduler;

    @Bean
    public ScheduleService schedulerService() {
        return new TodoScheduleService(scheduler);
    }
}
