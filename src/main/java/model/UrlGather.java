package model;

/**
 * All the url needed to send http request
 */
public class UrlGather {
    private static String APIKEY = "api_key=" + System.getenv("INPUT_API_KEY");

    public static final String URL = "https://api.currencyscoop.com/v1";

    public static String Currencies(){
        return URL +"/currencies" + "?type=fiat&" + APIKEY;
    }

    public static String Conversion(String from, String to, String amount){
        return URL + "/convert?from=" + from + "&to=" + to + "&amount=" + amount + "&" + APIKEY;
    }

    public static String Latest(String from, String to){
        return URL +"/latest?base=" + from + "&symbols=" + to + "&" + APIKEY;
    }
}
