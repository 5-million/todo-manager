package xyz.fivemillion.todomanager.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import xyz.fivemillion.todomanager.domain.TodoList;
import xyz.fivemillion.todomanager.service.send.SlackSendService;

@Slf4j
@Service
@RequiredArgsConstructor
public class SchedulerService {

    private final TodoList todoList;
    private final SlackSendService slackSendService;

    @Scheduled(cron = "0 50 08 * * *")
    public void morningAlarm() {
        slackSendService.sendMorning(todoList.getTodoList());
    }

    @Scheduled(cron = "0 0 10 * * *")
    public void alarm1() {
        slackSendService.send(todoList.getTodo(2));
    }

    @Scheduled(cron = "0 0 13 * * *")
    public void alarm2() {
        slackSendService.send(todoList.getTodo(1));
    }

    @Scheduled(cron = "0 0 22 * * *")
    public void alarm3() {
        slackSendService.send(todoList.getTodo(2));
    }

    @Scheduled(cron = "0 0 21 * * *")
    public void alarm4() {
        slackSendService.sendNight(todoList.getTodoList());
    }
}
