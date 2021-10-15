package xyz.fivemillion.todomanager.job;

import lombok.SneakyThrows;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import xyz.fivemillion.todomanager.domain.Todo;
import xyz.fivemillion.todomanager.domain.User;
import xyz.fivemillion.todomanager.service.send.SlackSendService;

import java.util.List;

public class MorningMsgSender extends CustomJob {

    @SneakyThrows
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        ApplicationContext ctx = getApplicationContext(context);
        User user = (User) context.getJobDetail().getJobDataMap().get("user");
        List<Todo> todos = (List<Todo>) context.getJobDetail().getJobDataMap().get("todos");
        SlackSendService slackSendService = ctx.getBean(SlackSendService.class);

        slackSendService.sendAtMorning(user, todos.toArray(new Todo[0]));
    }
}
