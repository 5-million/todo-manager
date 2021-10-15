package xyz.fivemillion.todomanager.job;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import xyz.fivemillion.todomanager.domain.Todo;
import xyz.fivemillion.todomanager.service.send.SlackSendService;

@Slf4j
@Component
public class MsgSenderJob extends CustomJob {

    @SneakyThrows
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        Todo todo = (Todo) context.getJobDetail().getJobDataMap().get("todo");

        ApplicationContext ctx = getApplicationContext(context);
        SlackSendService slackSendService = ctx.getBean(SlackSendService.class);
        slackSendService.send(todo);
    }
}
