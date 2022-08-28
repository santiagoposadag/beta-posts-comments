package com.posada.santiago.betapostsandcomments.application.config;


import com.posada.santiago.betapostsandcomments.application.handlers.QueueHandler;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
    public static final String EXCHANGE = "core-posts";

    public static final String PROXY_QUEUE = "events.proxy";
    public static final String GENERAL_QUEUE = "events.general";

    public static final String PROXY_ROUTING_KEY = "routingKey.proxy";

    @Autowired
    private QueueHandler handler;

    @Bean
    public Queue generalQueue(){
        return new Queue(PROXY_QUEUE);
    }

    @Bean
    public TopicExchange getTopicExchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Binding BindingToGeneralQueue() {
        return BindingBuilder.bind(generalQueue()).to(getTopicExchange()).with(PROXY_ROUTING_KEY);
    }

    @RabbitListener(queues = GENERAL_QUEUE)
    public void listenToGeneralQueue(String received){
        handler.accept(received);
    }


}
