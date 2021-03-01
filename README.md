# app-protobuf-messaging
POC is implemented using gRPC, Protobuf, Spring boot, Kafka

Sample json to test 
http://localhost:8081/trade/send

{
"tradeId":"TRD5647474474",
"symbol":"MSGS",
"price":56.21,
"quantity":100,
"account":"IU94857",
"buySell":"BUY"
}


This one class rest controller, then rest controller calls gRpc service-> gRpc write message to Kafka
and then Kafka consumer reads message and logs it