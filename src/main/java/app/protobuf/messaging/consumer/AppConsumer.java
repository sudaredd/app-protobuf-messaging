package app.protobuf.messaging.consumer;

import app.protobuf.messaging.grpc.util.ProtoUtil;
import app.protobuf.messaging.model.HelloRequest;
import app.protobuf.messaging.model.TradeRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AppConsumer {


//    @KafkaListener(     topics = "greeting",            containerFactory = "kafkaListenerContainerFactory"    )
    public void greetingReceiver(@Payload byte[] buffer) {

        HelloRequest request = ProtoUtil.parse(buffer, HelloRequest.class);
        log.info("received request ::"+request);
    }

    @KafkaListener(
            topics = "trade_new",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void tradeNewReceiver(@Payload byte[] buffer) {
        TradeRequest tradeRequest = ProtoUtil.parse(buffer, TradeRequest.class);
        log.info("tradeNew request received::"+tradeRequest);
    }
}
