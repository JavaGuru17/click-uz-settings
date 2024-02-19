package uz.pdp.clickuzusers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ClickUzUsersApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClickUzUsersApplication.class, args);
    }

}
