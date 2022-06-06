package model.currency;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import model.Tool;
import model.UrlGather;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * online currency type
 * implements Currency interface
 */
public class CurrencyOnline extends CurrencyAbstract implements Currency{
    private List<Currencies> currencies = new ArrayList<>();
    private List<String> currenciesCode = new ArrayList<>();
    private Map<String, String> countryMap = new HashMap<>();

    public  Tool tool;
    public CurrencyOnline(Tool tool) throws FileNotFoundException {
        this.tool = tool;
        this.countryMap = Tool.readJson();
        this.currencies = super.currencies;
        this.currenciesCode = super.currenciesCode;
        super.countryMap = this.countryMap;
    }

    /**
     * Used to: find country corresponding currency and add into list
     * @param country the country want to find corresponding currency to add table list
     * @return
     * "true": add successfully
     * "exist" already added
     * null: other error from API
     */
    @Override
    public String addCurrency(String country) {
        String fullname = getCountryName(country);
        if (fullname == null) return null;
        String result = "no currency";
        JsonObject status = tool.httpRequest(UrlGather.Currencies());
        if (status == null) return null;
        if (status.has("result")) return "no currency";
        JsonObject apiResult = status.get("fiats").getAsJsonObject();
        for (Map.Entry res : apiResult.entrySet()) {
            JsonObject curCurrency = (JsonObject) res.getValue();
            JsonArray count = curCurrency.get("countries").getAsJsonArray();
            for (int j = 0; j < count.size(); j++) {
                String currCountry = count.get(j).getAsString();
                if (currCountry.contains(fullname)) {
                    String code = curCurrency.get("currency_code").getAsString();
                    String name = curCurrency.get("currency_name").getAsString();
                    Currencies newCurrency = new Currencies(code, name);
                    if (!currenciesCode.contains(code)) {
                        currencies.add(newCurrency);
                        currenciesCode.add(code);
                        result = "true";
                    } else result = "exist";
                }
            }
        }
        return result;
    }

}
