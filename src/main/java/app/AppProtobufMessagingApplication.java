package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class AppProtobufMessagingApplication {

    public static void main(String[] args) {

        SpringApplication.run(AppProtobufMessagingApplication.class, args);
    }
}
