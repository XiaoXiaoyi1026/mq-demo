package cn.itcast.mq.listener;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.Map;

/**
 * @author 20609
 */
@Component
public class SpringRabbitListener {

//    @RabbitListener(queues = "simple.queue")
//    public void listenerSimpleQueue(String msg) {
//        System.out.println("消费者接收到simple.queue消息: {" + msg + "}");
//    }

    @RabbitListener(queues = "simple.queue")
    public void listenerWorkQueue1(String msg) throws InterruptedException {
        System.out.println("消费者1=========>接收到simple.queue消息: {" + msg + "}" + LocalTime.now());
        Thread.sleep(20);
    }

    @RabbitListener(queues = "simple.queue")
    public void listenerWorkQueue2(String msg) throws InterruptedException {
        System.err.println("消费者2=========>接收到simple.queue消息: {" + msg + "}" + LocalTime.now());
        Thread.sleep(200);
    }

    /**
     * 监听fanout.queue1队列中的消息
     *
     * @param msg 消息
     */
    @RabbitListener(queues = "fanout.queue1")
    public void listenerSimpleQueue1(String msg) {
        System.out.println("消费者接收到fanout.queue1消息: {" + msg + "}");
    }

    /**
     * 监听fanout.queue2队列中的消息
     *
     * @param msg 消息
     */
    @RabbitListener(queues = "fanout.queue2")
    public void listenerSimpleQueue2(String msg) {
        System.out.println("消费者接收到fanout.queue2消息: {" + msg + "}");
    }

    /**
     * 监听xiaoxiaoyi.direct交换机的direct.queue1队列中的消息
     * 路由key为blue和red
     *
     * @param msg 消息
     */
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(value = "xiaoxiaoyi.direct", type = ExchangeTypes.DIRECT),
            value = @Queue("direct.queue1"),
            key = {"blue", "red"}
    ))
    public void listenerDirectQueue1(String msg) {
        System.out.println("消费者接收到direct.queue1消息: {" + msg + "}");
    }

    /**
     * 监听xiaoxiaoyi.direct交换机的direct.queue2队列中的消息
     * 路由key为yellow和red
     *
     * @param msg 消息
     */
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(value = "xiaoxiaoyi.direct", type = ExchangeTypes.DIRECT),
            value = @Queue("direct.queue2"),
            key = {"yellow", "red"}
    ))
    public void listenerDirectQueue2(String msg) {
        System.out.println("消费者接收到direct.queue2消息: {" + msg + "}");
    }

    /**
     * 使用topic话题交换机
     * # : 0个或多个单词 * : 1个单词
     *
     * @param msg 消息
     */
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(value = "xiaoxiaoyi.topic", type = ExchangeTypes.TOPIC),
            value = @Queue("topic.queue1"),
            key = "china.#"
    ))
    public void listenerTopicQueue1(String msg) {
        System.out.println("消费者接收到topic.queue1消息: {" + msg + "}");
    }

    /**
     * 使用topic话题交换机
     * # : 0个或多个单词 * : 1个单词
     *
     * @param msg 消息
     */
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(value = "xiaoxiaoyi.topic", type = ExchangeTypes.TOPIC),
            value = @Queue("topic.queue2"),
            key = "#.news"
    ))
    public void listenerTopicQueue2(String msg) {
        System.out.println("消费者接收到topic.queue2消息: {" + msg + "}");
    }

    /**
     * 监听object.queue的消息
     *
     * @param msg 消息
     */
    @RabbitListener(queues = "object.queue")
    public void listenerObjectQueue(Map<String, Object> msg) {
        System.out.println("消费者接收到object.queue消息: " + msg);
    }

}
