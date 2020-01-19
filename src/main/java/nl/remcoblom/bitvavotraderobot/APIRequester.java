package nl.remcoblom.bitvavotraderobot;

import com.bitvavo.api.Bitvavo;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

public class APIRequester {

    private static APIRequester instance;
    private static Bitvavo bitvavo;
    public static final String KEY_MARKET = "market";
    public static final String KEY_PRICE = "price";
    public static final String KEY_CURRENCY = "symbol";
    public static final String KEY_AVAILABLE = "available";
    public static final String KEY_SIDE = "side";
    public static final String KEY_AMOUNT_REMAINING = "amountRemaining";
    public static final String VALUE_BUY = "buy";
    public static final String VALUE_SELL = "sell";

    private APIRequester(){}

    private static JSONObject getAPIKey() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("APIKey");
        String keyAPIKey = "APIKEY";
        String keyAPISecret = "APISECRET";
        String valueAPIKey = resourceBundle.getString(keyAPIKey);
        String valueAPISecret = resourceBundle.getString(keyAPISecret);
        return new JSONObject("{" +
                keyAPIKey + ":" + valueAPIKey + "," +
                keyAPISecret + ":" + valueAPISecret + "," +
                "ACCESSWINDOW: 10000, " +
                "DEBUGGING: false }");
    }

    public static APIRequester getInstance() {
        if (instance == null) {
            instance = new APIRequester();
            bitvavo = new Bitvavo(getAPIKey());
        }
        return instance;
    }

    public Map<String,Double> getAvailableMarkets() {
        JSONArray arrayResponse = bitvavo.tickerPrice(new JSONObject());
        Map<String,Double> availableMarkets = new HashMap<>();
        for (int i = 0; i< arrayResponse.length(); i++) {
            JSONObject jsonObject = arrayResponse.getJSONObject(i);
            availableMarkets.put(jsonObject.getString(KEY_MARKET), jsonObject.getDouble(KEY_PRICE));
        }
        return availableMarkets;
    }

    public Map<String,Double> getAvailableAssets() {
        JSONArray arrayResponse = bitvavo.balance(new JSONObject());
        Map<String,Double> availableAssets = new HashMap<>();
        for (int i = 0; i< arrayResponse.length(); i++) {
            JSONObject jsonObject = arrayResponse.getJSONObject(i);
            availableAssets.put(jsonObject.getString(KEY_CURRENCY), jsonObject.getDouble(KEY_AVAILABLE));
        }
        return availableAssets;
    }

    public int getNumberOpenOrders(Market market) {
        return getOpenOrders(market).size();
    }

    public List<Order> getOpenOrders(Market market) {
        JSONObject jsonObjectRequest = new JSONObject("{" + KEY_MARKET + ":" + market.getName() + "}");
        JSONArray arrayResponse = bitvavo.ordersOpen(jsonObjectRequest);
        List<Order> openOrders = new ArrayList<>();
        for (int i = 0; i< arrayResponse.length(); i++) {
            JSONObject jsonObject = arrayResponse.getJSONObject(i);
            openOrders.add(new Order(market, jsonObject));
        }
        return openOrders;
    }

    public long getNumberOpenOrders(Market market, String buyOrSell) {
        List<Order> openOrders = getOpenOrders(market);
        return openOrders.stream().filter(o -> o.getSide().equals(buyOrSell)).count();
    }
}
