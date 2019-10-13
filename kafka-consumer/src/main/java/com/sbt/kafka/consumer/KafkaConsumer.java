package com.sbt.kafka.consumer;

import io.smallrye.reactive.messaging.kafka.KafkaMessage;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import javax.enterprise.context.ApplicationScoped;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;


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
    public CompletionStage<Void> processIncomingKafkaMessage(KafkaMessage<String, String> message) {
        return CompletableFuture.runAsync(() -> {
            log.info("Message key: {}", message.getKey());
            message.getHeaders().unwrap().forEach(header -> log.debug("Message header: {} : {}", header.key(), header.value()));
            log.info("Message from provider: {}", message.getPayload());
        });
    }
}
