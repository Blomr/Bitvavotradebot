package nl.remcoblom.bitvavotraderobot;

import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;

public class Market {

    private final Currency baseCurrency;
    private final Currency marketCurrency;
    private final double price;

    public Market(Currency baseCurrency, Currency marketCurrency, double price) {
        this.baseCurrency = baseCurrency;
        this.marketCurrency = marketCurrency;
        this.price = price;
    }

    public Market(JSONObject jsonObject) {
        String[] currencies = jsonObject.getString(APIRequester.KEY_MARKET).split("-");
        this.baseCurrency = Currency.valueOf(currencies[0]);
        this.marketCurrency = Currency.valueOf(currencies[1]);
        this.price = jsonObject.getDouble(APIRequester.KEY_AVAILABLE);
    }

    public static Optional<Market> fromString(String marketName, List<Market> markets) {
        return markets.stream().filter(m -> m.getName().equals(marketName)).findFirst();
    }
    public Currency getBaseCurrency() {
        return baseCurrency;
    }

    public Currency getMarketCurrency() {
        return marketCurrency;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return baseCurrency + "-" + marketCurrency;
    }

    @Override
    public String toString() {
        DecimalFormat decimalFormat = new DecimalFormat("#####.#########");
        return baseCurrency + "-" + marketCurrency + ":" + decimalFormat.format(price);
    }
}
