package com.sbt.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import javax.enterprise.context.ApplicationScoped;


/**
 * Простой Kafka консъюмер получающий строку в качестве сообщения
 */
@Slf4j
@ApplicationScoped
public class KafkaConsumer {

    /**
     * Получить и залогировать входящее сообщение
     * @param message - входящее сообщение
     */
    @Incoming("deduplicate")
    public void processIncomingKafkaMessage(String message) {
        log.info("Message from provider {}", message);
    }
}
