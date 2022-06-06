import model.currency.Currencies;
import model.currency.CurrencyOffline;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.List;

public class CurrencyOfflineTest {

    @Test
    public void testAddCurrencyAU() throws FileNotFoundException {
        CurrencyOffline currencyOffline = new CurrencyOffline();
        Assertions.assertEquals("true", currencyOffline.addCurrency("AU"));
        Assertions.assertEquals(1, currencyOffline.getCurrencies().size());
        Assertions.assertEquals(1, currencyOffline.getCurrenciesCode().size());
    }

    @Test
    public void testAddCurrencyCN() throws FileNotFoundException {
        CurrencyOffline currencyOffline = new CurrencyOffline();
        Assertions.assertEquals("true", currencyOffline.addCurrency("CN"));
        Assertions.assertEquals(1, currencyOffline.getCurrencies().size());
        Assertions.assertEquals(1, currencyOffline.getCurrenciesCode().size());
    }

    @Test
    public void testAddCurrencyUS() throws FileNotFoundException {
        CurrencyOffline currencyOffline = new CurrencyOffline();
        Assertions.assertEquals("true", currencyOffline.addCurrency("US"));
        Assertions.assertEquals(1, currencyOffline.getCurrencies().size());
        Assertions.assertEquals(1, currencyOffline.getCurrenciesCode().size());
    }

    @Test
    public void testAddCurrencyCA() throws FileNotFoundException {
        CurrencyOffline currencyOffline = new CurrencyOffline();
        Assertions.assertEquals("true", currencyOffline.addCurrency("CA"));
        Assertions.assertEquals(1, currencyOffline.getCurrencies().size());
        Assertions.assertEquals(1, currencyOffline.getCurrenciesCode().size());
    }

    @Test
    public void testAddCurrencyAUExist() throws FileNotFoundException {
        CurrencyOffline currencyOffline = new CurrencyOffline();
        Assertions.assertEquals("true", currencyOffline.addCurrency("AU"));
        Assertions.assertEquals(1, currencyOffline.getCurrencies().size());
        Assertions.assertEquals(1, currencyOffline.getCurrenciesCode().size());
        Assertions.assertEquals("exist", currencyOffline.addCurrency("AU"));
        Assertions.assertEquals(1, currencyOffline.getCurrencies().size());
        Assertions.assertEquals(1, currencyOffline.getCurrenciesCode().size());
    }

    @Test
    public void testAddCurrencyNot() throws FileNotFoundException {
        CurrencyOffline currencyOffline = new CurrencyOffline();
        Assertions.assertEquals("true", currencyOffline.addCurrency("CU"));
        Assertions.assertEquals(1, currencyOffline.getCurrencies().size());
        Assertions.assertEquals(1, currencyOffline.getCurrenciesCode().size());
        Assertions.assertEquals("AUD", currencyOffline.getCurrenciesCode().get(0));
    }


    @Test
    public void testGetCurrencies() throws FileNotFoundException {
        CurrencyOffline currencyOffline = new CurrencyOffline();
        List<Currencies> currenciesList = currencyOffline.getCurrencies();
        Assertions.assertEquals(0, currenciesList.size());
        currencyOffline.addCurrency("AU");
        currenciesList = currencyOffline.getCurrencies();
        Assertions.assertEquals(1, currenciesList.size());
    }

    @Test
    public void testGetCurrenciesCode() throws FileNotFoundException {
        CurrencyOffline currencyOffline = new CurrencyOffline();
        List<String> currenciesList = currencyOffline.getCurrenciesCode();
        Assertions.assertEquals(0, currenciesList.size());
        currencyOffline.addCurrency("AU");
        currenciesList = currencyOffline.getCurrenciesCode();
        Assertions.assertEquals(1, currenciesList.size());
    }

    @Test
    public void testGetCountryName() throws FileNotFoundException {
        CurrencyOffline currencyOffline = new CurrencyOffline();
        Assertions.assertEquals("Australia", currencyOffline.getCountryName("AU"));
        Assertions.assertEquals("United States", currencyOffline.getCountryName("US"));
    }

    @Test
    public void testDelete() throws FileNotFoundException {
        CurrencyOffline currencyOffline = new CurrencyOffline();
        currencyOffline.addCurrency("AU");
        currencyOffline.addCurrency("US");
        List<Currencies> currenciesList = currencyOffline.getCurrencies();
        List<String> currenciesCodeList = currencyOffline.getCurrenciesCode();
        Assertions.assertEquals(2, currenciesList.size());
        Assertions.assertEquals(2, currenciesCodeList.size());
        Assertions.assertEquals("true", currencyOffline.delete("AUD"));
        Assertions.assertEquals(1, currenciesList.size());
        Assertions.assertEquals(1, currenciesCodeList.size());
    }

    @Test
    public void testDeleteFail() throws FileNotFoundException {
        CurrencyOffline currencyOffline = new CurrencyOffline();
        currencyOffline.addCurrency("AU");
        currencyOffline.addCurrency("US");
        List<Currencies> currenciesList = currencyOffline.getCurrencies();
        List<String> currenciesCodeList = currencyOffline.getCurrenciesCode();
        Assertions.assertEquals(2, currenciesList.size());
        Assertions.assertEquals(2, currenciesCodeList.size());
        Assertions.assertNull(currencyOffline.delete("CAD"));
        Assertions.assertEquals(2, currenciesList.size());
        Assertions.assertEquals(2, currenciesCodeList.size());
    }

    @Test
    public void testRemove() throws FileNotFoundException {
        CurrencyOffline currencyOffline = new CurrencyOffline();
        Assertions.assertEquals("empty", currencyOffline.remove());
        currencyOffline.addCurrency("AU");
        currencyOffline.addCurrency("US");
        List<Currencies> currenciesList = currencyOffline.getCurrencies();
        List<String> currenciesCodeList = currencyOffline.getCurrenciesCode();
        Assertions.assertEquals(2, currenciesList.size());
        Assertions.assertEquals(2, currenciesCodeList.size());
        Assertions.assertEquals("true", currencyOffline.remove());
        Assertions.assertEquals(0, currenciesList.size());
        Assertions.assertEquals(0, currenciesCodeList.size());
    }

}
