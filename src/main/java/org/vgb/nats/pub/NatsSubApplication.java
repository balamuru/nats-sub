package org.vgb.nats.pub;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication

public class NatsSubApplication implements ApplicationRunner {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private NatsSubscriberService natsSubscriberService;

    @Value("${nats.topic}")
    private String topic;

    @Value("${nats.workqueue}")
    private String workQueue;

    public static void main(String[] args) {
        new SpringApplicationBuilder(NatsSubApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        while (true) {
            final String msg = natsSubscriberService.subscribe(topic, workQueue);
            logger.info("Received message [{}] on topic [{}]",  msg, topic);
        }
    }
}
