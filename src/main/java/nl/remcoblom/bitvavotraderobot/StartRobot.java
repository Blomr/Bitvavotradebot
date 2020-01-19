package nl.remcoblom.bitvavotraderobot;

public class StartRobot
{
    private static int pauseInMinutes = 30;

    public static void main( String[] args )
    {
        BitvavoRobot robot = new BitvavoRobot(pauseInMinutes);
        robot.start();
        System.exit(0);

//        JSONObject credentialsJson = new JSONObject("{" +
//                "APIKEY: ca33ddd50d3b7778a5a70ac074bfe409c2671a6087ae1a2d64bcf5e10b33b353, " +
//                "APISECRET: 522b860e75bbb800ffdaaf9dda1e3a5d5bc236ee20498974c21d190e48b7dec14a9088ca6988cc127d7093dad9199219d0acbb26e8b24d19510e9295d8a0b3e0, " +
//                "ACCESSWINDOW: 10000, " +
//                "DEBUGGING: false }");
//        Bitvavo bitvavo = new Bitvavo(credentialsJson);
//
//        System.out.println( bitvavo.time());
//
//        JSONArray arrayResponse = bitvavo.markets(new JSONObject());
//        System.out.println(arrayResponse);
//        for(int i = 0; i < arrayResponse.length(); i ++) {
//            System.out.println(arrayResponse.getJSONObject(i));
//        }
//
//        JSONObject marketsJson = new JSONObject("{" +
//                "market: BTC-EUR" +
//                "}");
//        JSONArray arrayResponse2 = bitvavo.markets(marketsJson);
//        System.out.println();
//        for(int j = 0; j < arrayResponse2.length(); j++) {
//            System.out.println(arrayResponse2.getJSONObject(j));
//        }

        // BitvavoRequest
    }
}
