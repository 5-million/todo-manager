package xyz.fivemillion.todomanager.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import xyz.fivemillion.todomanager.domain.TodoList;
import xyz.fivemillion.todomanager.service.send.SlackSendService;

@RestController
@RequiredArgsConstructor
public class MessageController {

    private final SlackSendService slackSendService;
    private final TodoList todoList;

    @GetMapping("/api/v1/slack/send/{index}")
    private void sendToSlack(@PathVariable("index") int index) {
        slackSendService.send(todoList.getTodo(index));
    }

    @GetMapping("/api/v1/slack/send/morning")
    private void sendToSlackMorning() {
        slackSendService.sendMorning(todoList.getTodoList());
    }

    @GetMapping("/api/v1/slack/send/night")
    private void sendToSlackNight() {
        slackSendService.sendNight(todoList.getTodoList());
    }
}
