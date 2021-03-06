package app.protobuf.messaging.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TradeNew {
    private String tradeId;
    private String symbol;
    private double price;
    private int quantity;
    private String account;
    private String buySell;
}
