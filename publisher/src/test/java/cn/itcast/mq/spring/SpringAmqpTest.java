package cn.itcast.mq.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringAmqpTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testSendMessage2SimpleQueue() {
        String queueName = "simple.queue";
        String message = "hello, spring amqp!";
        rabbitTemplate.convertAndSend(queueName, message);
    }

    @Test
    public void testSendMessage2WorkQueue() throws InterruptedException {
        String queueName = "simple.queue";
        String message = "hello, spring amqp!";
        for (int i = 1; i <= 50; i++) {
            rabbitTemplate.convertAndSend(queueName, message + i);
            Thread.sleep(20);
        }
    }

    @Test
    public void testSendMessage2FanoutExchange() {
        // 交换机名称
        String exchangeName = "xiaoxiaoyi.fanout";
        // 消息
        String message = "hello!";
        // 发送消息到交换机
        rabbitTemplate.convertAndSend(exchangeName, "", message);
    }

    @Test
    public void testSendMessage2DirectExchange() {
        // 交换机名称
        String exchangeName = "xiaoxiaoyi.direct";
        // 消息
        String message = "hello red!";
        // 发送消息到交换机
        rabbitTemplate.convertAndSend(exchangeName, "red", message);
    }

    @Test
    public void testSendMessage2TopicExchange() {
        // 交换机名称
        String exchangeName = "xiaoxiaoyi.topic";
        // direct key
        String key = "china.weather";
        // 消息
        String message = "天气真好!!";
        // 发送消息到交换机
        rabbitTemplate.convertAndSend(exchangeName, key, message);
    }

    @Test
    public void testSendObjectMessage2ObjectQueue() {
        String queueName = "object.queue";
        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("xiaoxiaoyi", "genshin");
        rabbitTemplate.convertAndSend(queueName, objectMap);
    }
}
