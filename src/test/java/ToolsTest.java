import com.google.gson.JsonObject;
import model.Tool;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ToolsTest {
    @Test
    public void testAboutData() {
        List<String> result = Tool.aboutData();
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals("NAME: Concurrency Conversion App", result.get(0));
        Assertions.assertEquals("Author: Lavender Li (Shutong Li) \n" +
                "References: \n\t Currency Scoop: https://currencyscoop.com/api-documentation" +
                "\n\t Imgur: https://apidocs.imgur.com/ " + "\n\t World Map: https://github" +
                ".com/controlsfx/controlsfx", result.get(1));
    }

    @Test
    public void testCacheData() {
        List<String> result = Tool.cacheData();
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals("Choose which ways to get data", result.get(0));
        Assertions.assertEquals("The cache has this data, do you want to use it? \n Yes: use data in cache " +
                "\n No: use data from API", result.get(1));
    }

    @Test
    public void testHttpRequest() {
        Tool tool = mock(Tool.class);
        JsonObject result = new JsonObject();
        result.addProperty("code", "200");
        when(tool.httpRequest(anyString())).thenReturn(result);
        Assertions.assertEquals(result, tool.httpRequest("https://api.currencyscoop.com/v1"));
        Assertions.assertEquals("200", tool.httpRequest("https://api.currencyscoop.com/v1").get("code").getAsString());
    }

    @Test
    public void testReadJson() throws FileNotFoundException {
        Map<String, String> result = Tool.readJson();
        Assertions.assertNotNull(result);
    }

}
