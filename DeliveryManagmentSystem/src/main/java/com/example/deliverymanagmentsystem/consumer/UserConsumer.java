package com.example.deliverymanagmentsystem.consumer;

import com.example.deliverymanagmentsystem.config.MessagingCinfig;
import com.example.deliverymanagmentsystem.model.user.User;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class UserConsumer {
    @RabbitListener(queues = MessagingCinfig.QUEUE)
    public void consumeFromQueue(User user){
        System.out.println("Message recieved!");
    }
}
