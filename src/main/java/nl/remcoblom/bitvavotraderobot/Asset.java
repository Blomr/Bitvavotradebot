package nl.remcoblom.bitvavotraderobot;

import org.json.JSONObject;

import static nl.remcoblom.bitvavotraderobot.APIRequester.KEY_AVAILABLE;
import static nl.remcoblom.bitvavotraderobot.APIRequester.KEY_CURRENCY;

public class Asset {

    private final Currency currency;
    private final double amount;

    public Asset(Currency currency, double amount) {
        this.currency = currency;
        this.amount = amount;
    }

    public Asset(JSONObject jsonObject) {
        this.currency = Currency.valueOf(jsonObject.getString(KEY_CURRENCY));
        this.amount = jsonObject.getDouble(KEY_AVAILABLE);
    }

    public Currency getCurrency() {
        return currency;
    }

    public double getAmount() {
        return amount;
    }

    public double getValueInOtherCurrency(Market market) {
        return amount * market.getPrice();
    }

    @Override
    public String toString() {
        return currency + ": " + amount;
    }
}
