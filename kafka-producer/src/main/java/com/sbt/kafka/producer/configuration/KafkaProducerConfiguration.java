package com.sbt.kafka.producer.configuration;

import lombok.Getter;
import lombok.Setter;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;


/**
 * Класс с настройками для продюсера Kafka
 */
@Setter
@Getter
@ApplicationScoped
public class KafkaProducerConfiguration {

    /**
     * Максимальное количество сообщений, отправляемых продюсером
     */
    @ConfigProperty(name = "kafka.producer.limit")
    int limit;
}
