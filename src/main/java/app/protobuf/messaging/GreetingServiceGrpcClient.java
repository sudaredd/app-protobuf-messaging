package app.protobuf.messaging;

import app.protobuf.messaging.model.GreetingServiceGrpc;
import app.protobuf.messaging.model.HelloRequest;
import app.protobuf.messaging.model.HelloResponse;
import app.protobuf.messaging.model.Sentiment;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Arrays;
import java.util.Collections;

public class GreetingServiceGrpcClient {
    public static void main(String[] args) {
        ManagedChannel managedChannel = ManagedChannelBuilder
                                        .forAddress("localhost",9090)
                .usePlaintext(true)
                .build();

        GreetingServiceGrpc.GreetingServiceBlockingStub greetingServiceBlockingStub = GreetingServiceGrpc.newBlockingStub(managedChannel);
        HelloResponse greeting = greetingServiceBlockingStub.greeting(HelloRequest.newBuilder()
                .setFirstName("Sudar")
                .setLastname("Kasireddy")
                .setAge(33)
                .setSentiment(Sentiment.HAPPY)
                .addAllHobbies(Arrays.asList("Cricket,Swimming, Jagging".split(",")))
                .putAllBagOfTricks(Collections.singletonMap("trick1","Never trust others"))
                .build());
        managedChannel.shutdownNow();
        System.out.println("response from grpc:::"+greeting.getGreeting());

    }
}
