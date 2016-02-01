package n2ru.tagcloud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@EntityScan(basePackages = {"n2ru.tagcloud.domain"})
@SpringBootApplication
public class Application {

	public static final Logger LOG = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
        System.out.println("Logging level"+System.getenv("LOGGING_LEVEL"));
        LOG.info("=====Test=======");
    }
}