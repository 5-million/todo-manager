package xyz.fivemillion.todomanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TodoManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodoManagerApplication.class, args);
    }

}
