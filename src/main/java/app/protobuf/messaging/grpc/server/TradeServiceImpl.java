package app.protobuf.messaging.grpc.server;

import app.protobuf.messaging.grpc.util.ProtoUtil;
import app.protobuf.messaging.model.TradeResponse;
import app.protobuf.messaging.model.TradeServiceGrpc;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@GrpcService
public class TradeServiceImpl extends TradeServiceGrpc.TradeServiceImplBase {

    private final KafkaTemplate<String, byte[]> kafkaTemplate;

    public void sendTrade(app.protobuf.messaging.model.TradeRequest request,
                          io.grpc.stub.StreamObserver<app.protobuf.messaging.model.TradeResponse> responseObserver) {

        log.info("trade received to send::" + request);

        byte[] bytes = ProtoUtil.toByteArrayDelimited(request);
        log.info("send new trade {} to Kafka", request.getTrade().getTradeId());
        kafkaTemplate.send("trade_new", bytes);
        responseObserver.onNext(TradeResponse.newBuilder()
                .setTradeId(request.getTrade().getTradeId()).build());
        log.info("returning response to client");
        responseObserver.onCompleted();
    }

    /**
     *
     */
    public void findByTradeId(app.protobuf.messaging.model.FindTradeById request,
                              io.grpc.stub.StreamObserver<app.protobuf.messaging.model.Trade> responseObserver) {

    }

    /**
     *
     */
    public void findAll(com.google.protobuf.Empty request,
                        io.grpc.stub.StreamObserver<app.protobuf.messaging.model.TradeResponseList> responseObserver) {

    }
}
