package com.example.deliverymanagmentsystem.consumer;

import com.example.deliverymanagmentsystem.config.MessagingConfig;
import com.example.deliverymanagmentsystem.model.user.User;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class UserConsumer {
    @RabbitListener(queues = MessagingConfig.QUEUE)
    public void consumeFromQueue(User user){
        System.out.println("Message recieved!");
        System.out.println(user);
    }
}
