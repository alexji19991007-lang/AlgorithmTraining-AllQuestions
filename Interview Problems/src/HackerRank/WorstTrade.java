package HackerRank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorstTrade {
    static class Trade {
        String tradeId;
        String instrumentId;
        String actionType;
        int price;
        int volume;

        public Trade(String tradeId, String instrumentId, String actionType, int price, int volume) {
            this.tradeId = tradeId;
            this.instrumentId = instrumentId;
            this.actionType = actionType;
            this.price = price;
            this.volume = volume;
        }

        public int calculatePnL(int curPrice) {
            return actionType.equals("BUY") ? curPrice - price : price - curPrice;
        }
    }

    static class Instrument {
        String instrumentId;
        int curPrice;
        List<Trade> tradeList;

        public Instrument(String id, int price) {
            this.instrumentId = id;
            this.curPrice = price;
            this.tradeList = new ArrayList<>();
        }
    }

    Map<String, Instrument> instrumentIdToInstrumentMap;
    Map<String, Trade> tradeIdToTradeMap;

    public WorstTrade() {
        this.instrumentIdToInstrumentMap = new HashMap<>();
        this.tradeIdToTradeMap = new HashMap<>();
    }
}
