package com.sbt.kafka.producer;

import com.sbt.doc.generator.DocGenerator;
import com.sbt.kafka.producer.configuration.KafkaProducerConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.eclipse.microprofile.reactive.streams.operators.PublisherBuilder;
import org.eclipse.microprofile.reactive.streams.operators.ReactiveStreams;

import javax.enterprise.context.ApplicationScoped;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

/**
 * Имлпементация продюсера Kafka
 */
@Slf4j
@ApplicationScoped
public class KafkaProducer {
    /**
     * Генератор документов, отправялемых продюсером
     */
    Supplier<String> docGenerator;

    /**
     * Конфигурация продюсера
     */
    KafkaProducerConfiguration configuration;

    KafkaProducer(DocGenerator docGenerator, KafkaProducerConfiguration configuration) {
        this.docGenerator = docGenerator::generateDoc;
        this.configuration = configuration;
    }

    /**
     * Метод создающий стрим для отпправки сообщений
     * @return - стрим для отправки сообщений
     */
    @Outgoing("deduplicate")
    public PublisherBuilder<String> sendMessagesToKafka() {
        return ReactiveStreams.generate(docGenerator).limit(configuration.getLimit());
    }
}
