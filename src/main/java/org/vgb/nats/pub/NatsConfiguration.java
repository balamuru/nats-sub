package org.vgb.nats.pub;

import io.nats.client.Connection;
import io.nats.client.Nats;
import io.nats.client.Options;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class NatsConfiguration {

    @Bean
    Connection connection( @Value("${nats.uri}") String natsUri) throws IOException, InterruptedException {
                final Options options = new Options.Builder()
                .server(natsUri)
                .build();

        return Nats.connect(options);
    }

}
