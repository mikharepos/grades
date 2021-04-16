package configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"controller", "service", "security"})
@EntityScan("model")
@EnableJpaRepositories("repository")
public class ApplicationRun {

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(ApplicationRun.class);
        SpringApplication.run(ApplicationRun.class, args);
        logger.info("Приложение запущено...");


    }
}
