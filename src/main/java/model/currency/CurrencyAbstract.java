package model.currency;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Some same functions in online and offline mode.
 */
public class CurrencyAbstract {
    public List<Currencies> currencies = new ArrayList<>();
    public List<String> currenciesCode = new ArrayList<>();
    public Map<String, String> countryMap = new HashMap<>();

    public List<Currencies> getCurrencies() { return currencies; }

    public List<String> getCurrenciesCode() { return currenciesCode; }

    public void setCurrencies(List<Currencies> currencies) {
        this.currencies = currencies;
    }

    public void setCurrenciesCode(List<String> currenciesCode) {
        this.currenciesCode = currenciesCode;
    }

    public String getCountryName(String country) { return countryMap.get(country); }

    /**
     * Used to: delete one currency from table list
     * @param code the currency code
     * @return delete successfully or not
     */
    public String delete(String code) {
        String result = null;
        for (int i = 0; i < currencies.size(); i ++) {
            if (currencies.get(i).code.equalsIgnoreCase(code)) {
                currencies.remove(i);
                currenciesCode.remove(i);
                result = "true";
            }
        }
        return result;
    }

    /**
     * Used to: remove all the currency in table list
     * @return remove successfully or not
     */
    public String remove() {
        String result = null;
        if (currencies.size() == 0) result = "empty";
        else {
            currencies.clear();
            currenciesCode.clear();
            result = "true";
        }
        return result;
    }
}
