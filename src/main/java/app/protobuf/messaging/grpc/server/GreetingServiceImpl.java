package app.protobuf.messaging.grpc.server;

import app.protobuf.messaging.grpc.util.ProtoUtil;
import app.protobuf.messaging.model.GreetingServiceGrpc;
import app.protobuf.messaging.model.HelloResponse;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

@RequiredArgsConstructor(onConstructor_ = @Autowired)
@GrpcService
public class GreetingServiceImpl extends GreetingServiceGrpc.GreetingServiceImplBase {

    private final KafkaTemplate<String, byte[]> kafkaTemplate;

    public void greeting(app.protobuf.messaging.model.HelloRequest request,
                         io.grpc.stub.StreamObserver<app.protobuf.messaging.model.HelloResponse> responseObserver) {
        String response = String.join("_", request.getFirstName(), request.getLastname());

        byte[] bytes = ProtoUtil.toByteArrayDelimited(request);
        HelloResponse helloResponse = HelloResponse.newBuilder().setGreeting(response).build();
        kafkaTemplate.send("greeting", bytes);
        responseObserver.onNext(helloResponse);
        responseObserver.onCompleted();
    }
}
