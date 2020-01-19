package nl.remcoblom.bitvavotraderobot;

import org.json.JSONObject;

public class Order {

    private final Market market;
    private final String side;
    private final double amount;
    private final double price;

    public Order(Market market, JSONObject jsonObject) {

        if (!jsonObject.getString(APIRequester.KEY_MARKET).equals(market.getName())) {
            throw new IllegalArgumentException("Order() - market");
        }
        this.market = market;
        String sideValue = jsonObject.getString(APIRequester.KEY_SIDE);
        if (!isValidValue(sideValue)) {
            throw new IllegalArgumentException("Order() - side");
        }
        this.side = sideValue;
        this.amount = jsonObject.getDouble(APIRequester.KEY_AMOUNT_REMAINING);
        this.price = jsonObject.getDouble(APIRequester.KEY_PRICE);
    }

    private boolean isValidValue(String value) {
        return value.equals(APIRequester.VALUE_BUY) || value.equals(APIRequester.VALUE_SELL);
    }

    public String getSide() {
        return side;
    }
}
