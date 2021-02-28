package app.protobuf.messaging;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "app.trade")
public class Properties {
    private String kafkaPaymentsAddress;
    private String kafkaConsumerGroup;
}
