package app.protobuf.messaging.repository;

import app.protobuf.messaging.model.TradeNew;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TradeRepository {

    @Insert("INSERT INTO Trade( tradeId, symbol, price, quantity, account, buySell) " +
            " VALUES (#{tradeId}, #{symbol}, #{price}, #{quantity}, #{account}, #{buySell})")
    public int insert(TradeNew tradeNew);
}
