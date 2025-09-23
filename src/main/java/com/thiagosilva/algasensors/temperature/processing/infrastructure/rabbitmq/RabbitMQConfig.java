package com.thiagosilva.algasensors.temperature.processing.infrastructure.rabbitmq;

import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class RabbitMQConfig {

    public static final String FANOUT_EXCHANGE_NAME = "temperature-processing.temperature-received.v1.e";

    /*
     * Carrega o bean do jackson para dentro do rabbitmq
     * fazendo com que os payloads complexos que forem 
     * publiclados em filas através dele sejam serializados
     * automaticamente
    */
    @Bean
    public Jackson2JsonMessageConverter jackson2XmlMessageConverter(ObjectMapper mapper) {
        return new Jackson2JsonMessageConverter(mapper);
    }


    /*
     * Esse bean do RabbitAdmin é necessário porque sem ele
     * o java não consegue criar exchanges, e queues, etc
     * de forma automatizada, já que o spring não provê ele
     * de forma automática
    */
    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory factory) {
        return new RabbitAdmin(factory);
    }

    /*
     * Para criar a exchange automaticamente , foi preciso também
     * criar um inicializador para chamar o RabbitAdmin fazendo
     * com que ele crie a exchange (RabbitMQInitializer)
    */
    @Bean
    public FanoutExchange exchange() {
        return ExchangeBuilder.fanoutExchange(
            FANOUT_EXCHANGE_NAME
        ).build();
    }

}
