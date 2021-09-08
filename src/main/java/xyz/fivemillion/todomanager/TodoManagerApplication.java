package xyz.fivemillion.todomanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TodoManagerApplication {

    private static final String PROPERTIES = "spring.config.location=" +
            "classpath:/application.yml" +
            ",classpath:/todos.yml";

    public static void main(String[] args) {
        new SpringApplicationBuilder(TodoManagerApplication.class)
                .properties(PROPERTIES)
                .run(args);
//        SpringApplication.run(TodoManagerApplication.class, args);
    }

}
