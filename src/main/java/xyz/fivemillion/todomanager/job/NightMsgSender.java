package xyz.fivemillion.todomanager.job;

import lombok.SneakyThrows;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import xyz.fivemillion.todomanager.domain.Todo;
import xyz.fivemillion.todomanager.domain.User;
import xyz.fivemillion.todomanager.service.send.SlackSendService;

import java.util.List;

public class NightMsgSender implements Job {

    @SneakyThrows
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        ApplicationContext ctx = (ApplicationContext) context.getScheduler().getContext().get("applicationContext");
        User user = (User) context.getJobDetail().getJobDataMap().get("user");
        List<Todo> todos = (List<Todo>) context.getJobDetail().getJobDataMap().get("todos");
        SlackSendService slackSendService = ctx.getBean(SlackSendService.class);

        slackSendService.sendAyNight(user, todos.toArray(new Todo[0]));
    }
}
