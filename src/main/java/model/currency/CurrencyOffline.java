package model.currency;
import model.Tool;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * offline currency type
 * implements Currency interface
 */
public class CurrencyOffline extends CurrencyAbstract implements Currency {
    private List<Currencies> currencies = new ArrayList<>();
    private List<String> currenciesCode = new ArrayList<>();
    private Map<String, String> countryMap = new HashMap<>();

    public CurrencyOffline() throws FileNotFoundException {
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
        String result = null;
        String code = null;
        Currencies newCur = null;
        if (country.equals("AU")) {
            newCur = new Currencies("AUD", "Australian dollar");
            code = "AUD";
        } else if (country.equals("CN")) {
            newCur = new Currencies("CNY", "Renminbi (Chinese) yuan");
            code = "CNY";
        } else if (country.equals("US")) {
            newCur = new Currencies("USD", "United States dollar");
            code = "USD";
        } else if (country.equals("CA")) {
            newCur = new Currencies("CAD", "Canadian dollar");
            code = "CAD";
        } else {
            newCur = new Currencies("AUD", "Australian dollar");
            code = "AUD";
        }
        if (!currenciesCode.contains(code)) {
            currencies.add(newCur);
            currenciesCode.add(code);
            result = "true";
        } else result = "exist";
        return result;
    }

}
