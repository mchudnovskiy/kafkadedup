package com.sbt.kafka.streams;

import com.sbt.kafka.streams.configuration.KafkaStreamsConfiguration;
import io.netty.util.internal.StringUtil;
import io.smallrye.reactive.messaging.annotations.Broadcast;
import io.smallrye.reactive.messaging.kafka.KafkaMessage;
import io.smallrye.reactive.messaging.kafka.SendingKafkaMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Produced;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import java.util.Base64;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@Slf4j
@ApplicationScoped
public class KafkaStreams {

    //TODO: забораться в том, какой подход лучше, kafka stream API или SmallRye reactive messaging

    KafkaStreamsConfiguration configuration;

    public KafkaStreams(KafkaStreamsConfiguration configuration) {
        this.configuration = configuration;
    }

    /*@Incoming("deduplicate")
    @Outgoing("deduplicate")
    @Broadcast
    public CompletionStage<KafkaMessage<String, String>> deduplicate(KafkaMessage<String, String> message) {
        return CompletableFuture.supplyAsync(
                () -> new SendingKafkaMessage<String, String>(null, message.getKey(),
                        // если пустой пейлоад, то ничего не делаем, иначе - все в верхний регистр
                        (StringUtil.isNullOrEmpty(message.getPayload()) ? message.getPayload() : message.getPayload().toUpperCase()),
                        null, null, message.getHeaders(), null)
        );
    }*/

    @Produces
    public Topology deduplicate() {
        StreamsBuilder builder = new StreamsBuilder();

        builder
                .stream(configuration.getTopicIn(), Consumed.with(Serdes.ByteArray(), Serdes.String()))
                .mapValues(value -> value.toUpperCase())
                .to(configuration.getTopicOut(), Produced.valueSerde(Serdes.String()));

        return builder.build();
    }
}
