package cn.itcast.mq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class SpringRabbitListener {
    /**
     * 监听简单队列
     *
     * @param message 消息
     */
    @RabbitListener(queues = "simple.queue")
    public void listenSimpleQueue(String message) {
        System.out.println("接收到simple.queue消息：" + message);
    }

    /**
     * 监听工作队列
     *
     * @param message 消息
     */
    @RabbitListener(queues = "work.queue")
    public void listenWorkQueue1(String message) throws InterruptedException {
        System.out.println("消费者1接收到work.queue消息：" + message + "  time：" + LocalTime.now());
        Thread.sleep(20);
    }

    @RabbitListener(queues = "work.queue")
    public void listenWorkQueue2(String message) throws InterruptedException {
        System.err.println("消费者2。。。接收到work.queue消息：" + message + "  time：" + LocalTime.now());
        Thread.sleep(200);
    }

    /**
     * 监听发布/订阅模式 广播fanout exchange
     *
     * @param message 消息
     */
    @RabbitListener(queues = "fanout.queue1")
    public void listenFanoutQueue1(String message) {
        System.out.println("消费者1接收到fanout.queue1消息：" + message);
    }

    @RabbitListener(queues = "fanout.queue2")
    public void listenFanoutQueue2(String message) {
        System.out.println("消费者2接收到fanout.queue2消息：" + message);
    }

}
