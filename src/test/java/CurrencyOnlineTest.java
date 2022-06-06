import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import model.Tool;
import model.currency.CurrencyOnline;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CurrencyOnlineTest {
    @Test
    public void testAdd() throws FileNotFoundException {
        Tool tool = mock(Tool.class);
        CurrencyOnline currencyOnline = new CurrencyOnline(tool);
        JsonObject result = new JsonObject();
        JsonObject australia = new JsonObject();
        JsonObject aus = new JsonObject();
        australia.add("AUD", aus);
        result.add("fiats", australia);
        aus.addProperty("currency_name", "Australian dollar");
        aus.addProperty("currency_code", "AUD");
        aus.addProperty("decimal_units", "2");
        JsonArray countries = new JsonArray();
        countries.add("Australia");
        aus.add("countries", countries);
        when(tool.httpRequest(anyString())).thenReturn(result);
        Assertions.assertEquals("true", currencyOnline.addCurrency("AU"));
    }

    @Test
    public void testAddFailed() throws FileNotFoundException {
        Tool tool = mock(Tool.class);
        CurrencyOnline currencyOnline = new CurrencyOnline(tool);
        JsonObject result = new JsonObject();
        JsonObject australia = new JsonObject();
        JsonObject aus = new JsonObject();
        australia.add("AUD", aus);
        result.add("fiats", australia);
        aus.addProperty("currency_name", "Australian dollar");
        aus.addProperty("currency_code", "AUD");
        aus.addProperty("decimal_units", "2");
        JsonArray countries = new JsonArray();
        countries.add("Australia");
        aus.add("countries", countries);
        when(tool.httpRequest(anyString())).thenReturn(result);
        Assertions.assertEquals("no currency", currencyOnline.addCurrency("CN"));
    }

    @Test
    public void testAddAPIWrong() throws FileNotFoundException {
        Tool tool = mock(Tool.class);
        CurrencyOnline currencyOnline = new CurrencyOnline(tool);
        when(tool.httpRequest(anyString())).thenReturn(null);
        Assertions.assertNull(currencyOnline.addCurrency("CN"));
    }

    @Test
    public void testAddNoCurrency() throws FileNotFoundException {
        Tool tool = mock(Tool.class);
        CurrencyOnline currencyOnline = new CurrencyOnline(tool);
        JsonObject result = new JsonObject();
        result.addProperty("result", "get failed");
        when(tool.httpRequest(anyString())).thenReturn(result);
        Assertions.assertEquals("no currency", currencyOnline.addCurrency("CN"));
    }

    @Test
    public void testAddCurrency() {
        CurrencyOnline currencyOnline = mock(CurrencyOnline.class);
        when(currencyOnline.addCurrency("AU")).thenReturn("true");
        Assertions.assertEquals("true", currencyOnline.addCurrency("AU"));
    }

    @Test
    public void testAddCurrencyFailed() {
        CurrencyOnline currencyOnline = mock(CurrencyOnline.class);
        when(currencyOnline.addCurrency("POI")).thenReturn("no currency");
        Assertions.assertEquals("no currency", currencyOnline.addCurrency("POI"));
    }
}
