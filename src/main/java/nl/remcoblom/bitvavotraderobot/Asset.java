package nl.remcoblom.bitvavotraderobot;

public class Asset {

    private final Currency currency;
    private final double amount;

    public Asset(Currency currency, double amount) {
        this.currency = currency;
        this.amount = amount;
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
