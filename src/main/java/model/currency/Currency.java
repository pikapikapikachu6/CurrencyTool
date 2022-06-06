package model.currency;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Currency interface
 */
public interface Currency {
    public List<Currencies> currencies = new ArrayList<>();
    public List<String> currenciesCode = new ArrayList<>();
    public Map<String, String> countryMap = new HashMap<>();

    public String addCurrency(String country);

    public List<Currencies> getCurrencies();
    public List<String> getCurrenciesCode();
    public void setCurrencies(List<Currencies> currencies);
    public void setCurrenciesCode(List<String> currenciesCode);

    public String delete(String code);
    public String remove();
}
