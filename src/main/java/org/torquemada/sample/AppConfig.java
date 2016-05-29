package org.torquemada.sample;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by torquemada on 3/3/16.
 */
@Configuration
public class AppConfig {
    @Bean
    public Person getPerson() {
        return new Person("Вася", 42);
    }
}
