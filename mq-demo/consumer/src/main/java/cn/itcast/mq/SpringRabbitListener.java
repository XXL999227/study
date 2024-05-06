package cn.itcast.mq;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
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


    /**
     * 监听发布/订阅模式 路由direct exchange
     *
     * @param message 消息
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "direct.queue1"),
            exchange = @Exchange(value = "direct.exchange", type = ExchangeTypes.DIRECT),
            key = {"red", "blue"}
    ))
    public void listenDirectQueue1(String message) {
        System.out.println("消费者1接收到direct.queue1消息：" + message);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "direct.queue2"),
            exchange = @Exchange(value = "direct.exchange", type = ExchangeTypes.DIRECT),
            key = {"red", "yellow"}
    ))
    public void listenDirectQueue2(String message) {
        System.out.println("消费者2接收到direct.queue2消息：" + message);
    }

    /**
     * 监听发布/订阅模式 主题topic exchange
     *
     * @param message 消息
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "topic.queue1"),
            exchange = @Exchange(value = "topic.exchange", type = ExchangeTypes.TOPIC),
            key = {"china.#"}
    ))
    public void listenTopicQueue1(String message) {
        System.out.println("消费者1接收到topic.queue1消息 china的所有消息：" + message);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "topic.queue2"),
            exchange = @Exchange(value = "topic.exchange", type = ExchangeTypes.TOPIC),
            key = {"#.news"}
    ))
    public void listenTopicQueue2(String message) {
        System.out.println("消费者2接收到topic.queue2消息 所有news的消息：" + message);
    }
}
