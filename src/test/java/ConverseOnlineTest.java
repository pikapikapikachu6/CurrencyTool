import com.google.gson.JsonObject;
import model.Tool;
import model.conversion.ConverseOnline;
import model.conversion.DataSQL;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ConverseOnlineTest {
    DataSQL dataSQL = new DataSQL();
    @Test
    public void testLatestSuccess() {
        Tool tool = mock(Tool.class);
        JsonObject result = new JsonObject();
        result.addProperty("date", "2022-05-15T14:49:28Z");
        result.addProperty("base", "CNY");
        JsonObject res = new JsonObject();
        res.addProperty("AUD", 0.21215382);
        result.add("rates", res);
        when(tool.httpRequest(anyString())).thenReturn(result);
        ConverseOnline converseOnline = new ConverseOnline(tool, dataSQL);
        Assertions.assertEquals("CNY to AUD rate is: 0.21215382", converseOnline.latest("CNY",
                "AUD"));
    }

    @Test
    public void testLatestFail() {
        Tool tool = mock(Tool.class);
        JsonObject result = new JsonObject();
        result.addProperty("result", "get failed");
        when(tool.httpRequest(anyString())).thenReturn(result);
        ConverseOnline converseOnline = new ConverseOnline(tool, dataSQL);
        Assertions.assertEquals("get failed", converseOnline.latest("CNY", "AUD"));
    }

    @Test
    public void testLatestNull() {
        Tool tool = mock(Tool.class);
        when(tool.httpRequest(anyString())).thenReturn(null);
        ConverseOnline converseOnline = new ConverseOnline(tool, dataSQL);
        Assertions.assertEquals("get failed", converseOnline.latest("CNY", "AUD"));
    }

    @Test
    public void testConversionSuccess() {
        Tool tool = mock(Tool.class);
        JsonObject result = new JsonObject();
        result.addProperty("timestamp", 1652630429);
        result.addProperty("date", "2022-05-15");
        result.addProperty("from", "CNY");
        result.addProperty("to", "AUD");
        result.addProperty("amount", 1);
        result.addProperty("value", 0.21209102);
        when(tool.httpRequest(anyString())).thenReturn(result);
        ConverseOnline converseOnline = new ConverseOnline(tool, dataSQL);
        Assertions.assertEquals("0.21209102", converseOnline.conversion("CNY", "AUD", "1", false));
    }

    @Test
    public void testConversionFailed() {
        Tool tool = mock(Tool.class);
        JsonObject result = new JsonObject();
        result.addProperty("result", "get failed");
        when(tool.httpRequest(anyString())).thenReturn(result);
        ConverseOnline converseOnline = new ConverseOnline(tool, dataSQL);
        Assertions.assertEquals("get failed", converseOnline.conversion("CNY", "AUD", "1", false));
    }

    @Test
    public void testConversionNull() {
        Tool tool = mock(Tool.class);
        when(tool.httpRequest(anyString())).thenReturn(null);
        ConverseOnline converseOnline = new ConverseOnline(tool, dataSQL);
        Assertions.assertEquals("get failed", converseOnline.conversion("CNY", "AUD", "1", false));
    }

    @Test
    public void testLatest() {
        ConverseOnline converseOnline = mock(ConverseOnline.class);
        when(converseOnline.latest("AUD", "USD")).thenReturn("AUD to USD rate is: 0.70408414");
        Assertions.assertEquals("AUD to USD rate is: 0.70408414", converseOnline.latest("AUD", "USD"));
    }

    @Test
    public void testLatestFailed() {
        ConverseOnline converseOnline = mock(ConverseOnline.class);
        when(converseOnline.latest("AUD", "USD")).thenReturn("get failed");
        Assertions.assertEquals("get failed", converseOnline.latest("AUD", "USD"));
    }

    @Test
    public void testConversion() {
        ConverseOnline converseOnline = mock(ConverseOnline.class);
        when(converseOnline.conversion("AUD", "USD", "1", false)).thenReturn("0.21322631");
        Assertions.assertEquals("0.21322631", converseOnline.conversion("AUD", "USD", "1", false));
    }

    @Test
    public void testConversionFail() {
        ConverseOnline converseOnline = mock(ConverseOnline.class);
        when(converseOnline.conversion("AUD", "USD", "100", false)).thenReturn("get failed");
        Assertions.assertEquals("get failed", converseOnline.conversion("AUD", "USD", "100", false));
    }
}
