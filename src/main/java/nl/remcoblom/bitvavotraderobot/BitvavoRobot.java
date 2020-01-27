package nl.remcoblom.bitvavotraderobot;

import java.util.ArrayList;
import java.util.List;

public class BitvavoRobot {

    private List<Asset> availableAssets;
    private static List<Market> availableMarkets;
    private long pauseInMillis;
    private boolean isActive = true;
    private Asset myEUR;

    public BitvavoRobot(long pauseInMillis) {
        this.pauseInMillis = pauseInMillis;
    }

    public BitvavoRobot(int pauseInMinutes) {
        this.pauseInMillis = pauseInMinutes * 60000;
    }

    public void start() {
        while (isActive) {
            APIRequester requester = APIRequester.getInstance();
            availableAssets = requester.getAvailableAssets();
            availableMarkets = requester.getAvailableMarkets();

            Asset myVET = new Asset(Currency.VET, availableAssets.get(Currency.VET.toString()));
            Asset myVTHO = new Asset(Currency.VTHO, availableAssets.get(Currency.VTHO.toString()));
            myEUR = new Asset(Currency.EUR, availableAssets.get(Currency.EUR.toString()));
            Market VETMarket = new Market(Currency.VET, Currency.EUR, availableMarkets.get(Currency.VET + "-" + Currency.EUR));
            Market VTHOMarket = new Market(Currency.VTHO, Currency.EUR, availableMarkets.get(Currency.VTHO + "-" + Currency.EUR));
            long VETBuyOrders = requester.getNumberOpenOrders(VETMarket, APIRequester.VALUE_BUY);
            long VETSellOrders = requester.getNumberOpenOrders(VETMarket, APIRequester.VALUE_SELL);
            long VTHOBuyOrders = requester.getNumberOpenOrders(VTHOMarket, APIRequester.VALUE_BUY);
            long VTHOSellOrders = requester.getNumberOpenOrders(VTHOMarket, APIRequester.VALUE_SELL);
            //        int numOrdersVET = requester.getNumberOpenOrders(VETMarket);
            //        int numOrdersVTHO = requester.getNumberOpenOrders(VTHOMarket);

            //        System.out.println(myVET);
            //        System.out.println(myVTHO);
            System.out.println(myEUR);
            //        System.out.println(VETMarket);
            //        System.out.println(VTHOMarket);
            //        System.out.println(myVET.getValueInOtherCurrency(VETMarket));
            //        System.out.println(myVTHO.getValueInOtherCurrency(VTHOMarket));
            //        System.out.println("Orders VET: " + numOrdersVET);
            //        System.out.println("Orders VTHO: " + numOrdersVTHO);
            System.out.println("VET - Buy orders: " + VETBuyOrders);
            System.out.println("VET - Sell orders: " + VETSellOrders);
            System.out.println("VTHO - Buy orders: " + VTHOBuyOrders);
            System.out.println("VTHO - Sell orders: " + VTHOSellOrders);
            System.out.println(requester.getOpenOrders());
            try {
                Thread.sleep(pauseInMillis);
            } catch (InterruptedException e) {

            }
        }
        System.exit(0);
    }

    public void stop() {
        isActive = false;
    }

    public static List<Market> getAvailableMarkets() {
        return new ArrayList<>(availableMarkets);
    }

    public Asset getMyEUR() {
        return myEUR;
    }
}
