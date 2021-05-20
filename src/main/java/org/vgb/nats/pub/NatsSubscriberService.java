package org.vgb.nats.pub;

import io.nats.client.Connection;
import io.nats.client.Message;
import io.nats.client.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class NatsSubscriberService {

    @Autowired
    private Connection connection;

    public String subscribe(String topic, String workQueue) throws InterruptedException {
        Subscription subscription = null;

        try {
            subscription = connection.subscribe(topic, workQueue);
            final Message msg = subscription.nextMessage(Duration.ZERO);
            return new String(msg.getData());
        } finally {
            if (subscription != null) {
                subscription.unsubscribe();
            }
        }

    }


}
