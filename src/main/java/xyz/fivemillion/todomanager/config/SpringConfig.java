package xyz.fivemillion.todomanager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xyz.fivemillion.todomanager.util.sender.Sender;
import xyz.fivemillion.todomanager.util.sender.SlackSender;

@Configuration
public class SpringConfig {

    @Bean
    public Sender sender() {
        return new SlackSender();
    }
}
