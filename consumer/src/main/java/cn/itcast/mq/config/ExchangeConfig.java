package cn.itcast.mq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 20609
 */
@Configuration
public class ExchangeConfig {

    /**
     * 定义fanout交换机
     */
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("xiaoxiaoyi.fanout");
    }

    /**
     * 定义消息队列1
     */
    @Bean
    public Queue fanoutQueue1() {
        return new Queue("fanout.queue1");
    }

    /**
     * 绑定消息队列1到交换机
     */
    @Bean
    public Binding fanoutBinding1(FanoutExchange fanoutExchange, Queue fanoutQueue1) {
        return BindingBuilder
                .bind(fanoutQueue1)
                .to(fanoutExchange);
    }

    /**
     * 定义消息队列2
     */
    @Bean
    public Queue fanoutQueue2() {
        return new Queue("fanout.queue2");
    }

    /**
     * 绑定消息队列2到交换机
     */
    @Bean
    public Binding fanoutBinding2(FanoutExchange fanoutExchange, Queue fanoutQueue2) {
        return BindingBuilder
                .bind(fanoutQueue2)
                .to(fanoutExchange);
    }

    /**
     * 声明一个消息队列
     *
     * @return 消息队列
     */
    @Bean
    public Queue objectQueue() {
        return new Queue("object.queue");
    }

}
