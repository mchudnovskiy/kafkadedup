package com.sbt.kafka.streams.configuration;

import lombok.Getter;
import lombok.Setter;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@Setter
@Getter
@ApplicationScoped
public class KafkaStreamsConfiguration {
    @ConfigProperty(name = "deduplicate.topic.in")
    String topicIn;

    @ConfigProperty(name = "deduplicate.topic.out")
    String topicOut;
}
