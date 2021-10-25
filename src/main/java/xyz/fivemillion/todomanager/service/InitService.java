package xyz.fivemillion.todomanager.service;

import lombok.RequiredArgsConstructor;
import org.quartz.SchedulerException;
import org.springframework.stereotype.Component;
import xyz.fivemillion.todomanager.domain.Todo;
import xyz.fivemillion.todomanager.domain.User;
import xyz.fivemillion.todomanager.dto.TodoScheduleRequest;
import xyz.fivemillion.todomanager.job.MorningMsgSender;
import xyz.fivemillion.todomanager.job.NightMsgSender;
import xyz.fivemillion.todomanager.job.MsgSenderJob;
import xyz.fivemillion.todomanager.service.scheduler.ScheduleService;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class InitService {

    private final TodoService todoService;
    private final ScheduleService scheduleService;
    private Map<User, List<Todo>> todoGroupByUser;

    @PostConstruct
    private void init() throws SchedulerException {
        todoGroupByUser = todoService.getAll().stream().collect(Collectors.groupingBy(Todo::getUser));
        initSchedule();
        initMorningAndNight();
    }

    private void initSchedule() throws SchedulerException {
        for (User user : todoGroupByUser.keySet()) {
            for (Todo todo : todoGroupByUser.get(user)) {
                scheduleService.register(MsgSenderJob.class, TodoScheduleRequest.build(todo));
            }
        }
    }

    private void initMorningAndNight() throws SchedulerException {
        for (User user : todoGroupByUser.keySet()) {
            List<Todo> todos = todoGroupByUser.get(user);

            String MORNING_CRON_EXP = "0 50 08 * * ?";
            TodoScheduleRequest morningRequest = buildTodoScheduleRequest(user, todos, MORNING_CRON_EXP);
            morningRequest.setGroup("m_smry");
            scheduleService.register(MorningMsgSender.class, morningRequest);

            String NIGHT_CRON_EXP = "0 0 21 * * ?";
            TodoScheduleRequest nightRequest = buildTodoScheduleRequest(user, todos, NIGHT_CRON_EXP);
            nightRequest.setGroup("n_smry");
            scheduleService.register(NightMsgSender.class, nightRequest);
        }
    }

    private TodoScheduleRequest buildTodoScheduleRequest(User user, List<Todo> todos, String cronExp) {
        TodoScheduleRequest request = new TodoScheduleRequest();
        request.setGroup(user.getUserId());
        request.getJobData().put("user", user);
        request.getJobData().put("todos", todos);
        request.setCronExp(cronExp);

        return request;
    }
}
