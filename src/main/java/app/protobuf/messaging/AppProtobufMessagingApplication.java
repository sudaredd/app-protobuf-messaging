package app.protobuf.messaging;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.ByteArrayDeserializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@EnableKafka
public class AppProtobufMessagingApplication {

    public static void main(String[] args) {

        SpringApplication.run(AppProtobufMessagingApplication.class, args);
    }

    @Configuration
    @AllArgsConstructor
    private static class Config {
        @Bean("kafkaListenerContainerFactory")
        public ConcurrentKafkaListenerContainerFactory<byte[], byte[]> kafkaPaymentsListener(Properties properties) {
            Map<String, Object> props = new HashMap<>();
            props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, properties.getKafkaPaymentsAddress());
            props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, ByteArrayDeserializer.class);
            props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ByteArrayDeserializer.class);
            props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
            props.put(ConsumerConfig.GROUP_ID_CONFIG, "cme-cbt");
            var consumerFactory = new DefaultKafkaConsumerFactory<byte[], byte[]>(props);
            var containerFactory = new ConcurrentKafkaListenerContainerFactory<byte[], byte[]>();
            containerFactory.setConsumerFactory(consumerFactory);
            containerFactory.getContainerProperties().setAckMode(ContainerProperties.AckMode.RECORD);
            return containerFactory;
        }
    }

    @Data
    @Component
    @ConfigurationProperties(prefix = "app.trade")
    class Properties {
        private String kafkaPaymentsAddress;
        private String kafkaConsumerGroup;
    }


}
