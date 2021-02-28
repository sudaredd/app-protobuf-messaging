package app.protobuf.messaging.controller;

import app.protobuf.messaging.grpc.server.TradeServiceImpl;
import app.protobuf.messaging.model.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
public class TradeController {

    @Autowired
    private TradeServiceImpl tradeService;
    @Autowired
    private ManagedChannel managedChannel;

    @PostMapping("/trade/send")
    public String sendTrade(@RequestBody TradeNew trade) {

        TradeRequest tradeRequest = TradeRequest.newBuilder()
                .setTrade(Trade.newBuilder()
                        .setTradeId(trade.getTradeId())
                        .setAccount(trade.getAccount())
                        .setBuySell(BuySell.valueOf(trade.getBuySell()))
                        .setSymbol(trade.getSymbol())
                        .setQuantity(trade.getQuantity())
                        .setPrice(trade.getPrice())
                        .build())
                .build();

        TradeServiceGrpc.TradeServiceBlockingStub tradeServiceBlockingStub = TradeServiceGrpc.newBlockingStub(managedChannel);
        TradeResponse tradeResponse = tradeServiceBlockingStub.sendTrade(tradeRequest);
        return tradeResponse.getTradeId();
    }
}
