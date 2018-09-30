package cn.haho.template;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    private static Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws IOException {
        logger.info("\nstart ------------ XXX服务----------------\n");
        SpringApplication.run(Application.class, args);
        logger.info("\nstarted! ------------ xxx服务已启动----------------\n");
    }
}
