package app.protobuf.messaging.model;

import lombok.Data;

@Data
public class TradeNew {
    private String tradeId;
    private String symbol;
    private double price;
    private int quantity;
    private String account;
    private String buySell;
}
