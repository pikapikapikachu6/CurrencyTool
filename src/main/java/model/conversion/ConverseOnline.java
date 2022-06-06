package model.conversion;
import com.google.gson.JsonObject;
import model.Tool;
import model.UrlGather;

/**
 * online conversion type
 * implements Converse interface
 */
public class ConverseOnline implements Converse {

    public Tool tool;
    DataSQL dataSQL;

    /**
     * All the operations related conversion all finished by this model
     * @param tool: some helper function
     * @param dataSQL: database
     */
    public ConverseOnline(Tool tool, DataSQL dataSQL) {
        this.tool = tool;
        this.dataSQL = dataSQL;
    }

    /**
     * Used to: check the data whether exists in database
     * @param from the currency want to transfer from
     * @param to the currency want to transfer to
     * @return
     * "1": exists in database
     * "0": not exists in database
     */
    @Override
    public String checkData(String from, String to) {
        return dataSQL.queryRateExist(from, to);
    }

    /**
     * Used to: get the saved exchange rate
     * @param from the currency want to transfer from
     * @param to the currency want to transfer to
     * @return the exchange rate obtained from cache
     */
    @Override
    public String getData(String from, String to) {
        String result = dataSQL.queryRate(from, to);
        if (result != null) {
            result = from + " to " + to + " rate is: " + result;
        }
        return result;

    }

    /**
     * Used to save the rate data into database
     * @param from the currency want to transfer from
     * @param to the currency want to transfer to
     * @param current the current rate get from API
     * @return
     * true: saved successfully
     * false: saved failed
     */
    @Override
    public Boolean save(String from, String to, String current) {
        return dataSQL.addRate(from, to, current);
    }

    /**
     * Used to: Get exchange rate
     * @param from the currency want to transfer from
     * @param to the currency want to transfer to
     * @return the current rate obtained from API
     */
    @Override
    public String latest(String from, String to) {
        JsonObject status = tool.httpRequest(UrlGather.Latest(from, to));
        if (status == null) return "get failed";
        if (status.has("result")) return "get failed";
        String res = from + " to " + to + " rate is: ";
        String rate = status.get("rates").getAsJsonObject().toString();
        rate = rate.substring(7, rate.length() - 1);
        String result = res + rate;
        return result;
    }

    /**
     * Used to: Get the conversion result from one currency to another
     * @param from the currency want to transfer from
     * @param to the currency want to transfer to
     * @param amount the amount value want to converse
     * @param cache choose use cache data or not (true: use from cache; false: use from API)
     * @return the conversion result value
     */
    @Override
    public String conversion(String from, String to, String amount, Boolean cache) {
        String result;
        if (cache) {
            System.out.println("use cache data");
            String rates = getData(from, to);
            rates = rates.substring(20);
            Double rate = Double.valueOf(rates);
            Double amounts = Double.valueOf(amount);
            result = String.valueOf(amounts * rate);
            System.out.println("use cache result: " + result);
        } else {
            JsonObject status = tool.httpRequest(UrlGather.Conversion(from, to, amount));
            if (status == null) return "get failed";
            if (status.has("result")) return "get failed";
            result = status.get("value").getAsString();
        }
        return result;
    }
}
