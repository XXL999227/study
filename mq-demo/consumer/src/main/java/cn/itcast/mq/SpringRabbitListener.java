package cn.itcast.mq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class SpringRabbitListener {
    @RabbitListener(queues = "simple.queue")
    public void listen(String message) {
        System.out.println("接收到simple.queue消息：" + message);
    }
}
