package cn.itcast.mq.helloworld;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringAmqpTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 测试发送消息到简单队列
     */
    @Test
    public void testSendMessageToSimpleQueue() {
        String message = "hello, spring amqp!";
        rabbitTemplate.convertAndSend("simple.queue", message);
        System.out.println("发送消息成功：【" + message + "】");
    }

    @Resource
    private RabbitAdmin rabbitAdmin;

    /**
     * 测试发送消息到工作队列
     */
    @Test
    public void testSendMessageToWorkQueue() {
        // 创建队列
        Queue queue = new Queue("work.queue", true, false, false);
        rabbitAdmin.declareQueue(queue);
        for (int i = 1; i <= 50; i++) {
            String message = "hello, spring amqp!————" + i;
            rabbitTemplate.convertAndSend("work.queue", message);
            System.out.println("发送消息成功：【" + message + "】");
        }
    }

    /**
     * 测试发送消息到发布/订阅模式 广播exchange
     */
    @Test
    public void testSendMessageToFanoutExchange() {
        // 交换机名称
        String exchangeName = "itcast.fanout";
        // 消息
        String message = "hello, fanout message!";
        // 发送消息，参数分别是：交互机名称、RoutingKey（暂时为空）、消息
        rabbitTemplate.convertAndSend(exchangeName, "", message);
    }
}
