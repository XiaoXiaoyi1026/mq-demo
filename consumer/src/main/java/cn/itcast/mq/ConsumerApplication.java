package cn.itcast.mq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author 20609
 */
@SpringBootApplication
public class ConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }

    /**
     * 自定义消息转换器(Spring自动装配原理)
     *
     * @return jackson对象转json数据的消息转换器, 常用
     */
    @Bean
    public MessageConverter messageConverter() {
        // 消息对象转json, 接受时反向
        return new Jackson2JsonMessageConverter();
    }
}
