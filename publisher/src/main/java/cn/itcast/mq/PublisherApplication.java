package cn.itcast.mq;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author 20609
 */
@SpringBootApplication
public class PublisherApplication {
    public static void main(String[] args) {
        SpringApplication.run(PublisherApplication.class);
    }

    /**
     * 自定义消息转换器(Spring自动装配原理)
     *
     * @return jackson对象转json数据的消息转换器, 常用
     */
    @Bean
    public MessageConverter messageConverter() {
        // 消息对象转json
        return new Jackson2JsonMessageConverter();
    }
}
