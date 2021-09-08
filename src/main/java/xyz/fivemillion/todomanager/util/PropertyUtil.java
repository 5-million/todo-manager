package xyz.fivemillion.todomanager.util;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:application.yml")
@RequiredArgsConstructor
public class PropertyUtil {

    private final Environment environment;

    public String getProperty(String key) {
        return environment.getProperty(key);
    }
}
