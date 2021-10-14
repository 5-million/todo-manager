package xyz.fivemillion.todomanager.service.send;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.fivemillion.todomanager.domain.Todo;
import xyz.fivemillion.todomanager.domain.User;
import xyz.fivemillion.todomanager.util.builder.payload.SlackPayloadBuilder;
import xyz.fivemillion.todomanager.util.sender.SlackSender;

@Service
@RequiredArgsConstructor
public class SlackSendService implements SendService {

    private final SlackPayloadBuilder slackPayloadBuilder;
    private final SlackSender slackSender;

    @Override
    public void send(Todo todo) {
        slackSender.send(todo.getUser().getWebhookUrl(), slackPayloadBuilder.build(todo));
    }

    @Override
    public void sendAtMorning(User user, Todo[] todos) {
        slackSender.send(user.getWebhookUrl(), slackPayloadBuilder.buildMorningMsg(todos));
    }

    @Override
    public void sendAyNight(User user, Todo[] todos) {
        slackSender.send(user.getWebhookUrl(), slackPayloadBuilder.buildNightMsg(todos));
    }
}
