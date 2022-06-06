import model.conversion.ConverseOffline;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ConverseOfflineTest {
    public ConverseOffline converseOffline = new ConverseOffline();
    @Test
    public void testLatestFromCNY() {
        Assertions.assertEquals("CNY to AUD rate is: 0.21322631", converseOffline.latest("CNY", "AUD"));
        Assertions.assertEquals("CNY to USD rate is: 0.15132848", converseOffline.latest("CNY", "USD"));
        Assertions.assertEquals("CNY to CAD rate is: 0.19519691", converseOffline.latest("CNY", "CAD"));
    }

    @Test
    public void testLatestFromAUD() {
        Assertions.assertEquals("AUD to CNY rate is: 4.65268768", converseOffline.latest("AUD", "CNY"));
        Assertions.assertEquals("AUD to USD rate is: 0.70408414", converseOffline.latest("AUD", "USD"));
        Assertions.assertEquals("AUD to CAD rate is: 0.90819024", converseOffline.latest("AUD", "CAD"));
    }

    @Test
    public void testLatestFromUSD() {
        Assertions.assertEquals("USD to AUD rate is: 1.42028479", converseOffline.latest("USD", "AUD"));
        Assertions.assertEquals("USD to CNY rate is: 6.60814157", converseOffline.latest("USD", "CNY"));
        Assertions.assertEquals("USD to CAD rate is: 1.28988879", converseOffline.latest("USD", "CAD"));
    }

    @Test
    public void testLatestFromCAD() {
        Assertions.assertEquals("CAD to AUD rate is: 1.10109089", converseOffline.latest("CAD", "AUD"));
        Assertions.assertEquals("CAD to USD rate is: 0.77526063", converseOffline.latest("CAD", "USD"));
        Assertions.assertEquals("CAD to CNY rate is: 5.12303201", converseOffline.latest("CAD", "CNY"));
    }

    @Test
    public void testConversionAmount1() {
        Assertions.assertEquals("0.21322631", converseOffline.conversion("CNY", "AUD", "1", false));
        Assertions.assertEquals("0.70408414", converseOffline.conversion("AUD", "USD", "1", false));
        Assertions.assertEquals("1.28988879", converseOffline.conversion("USD", "CAD", "1", false));
    }

    @Test
    public void testConversionAmount10() {
        Assertions.assertEquals("2.1322631", converseOffline.conversion("CNY", "AUD", "10", false));
        Assertions.assertEquals("7.0408414", converseOffline.conversion("AUD", "USD", "10", false));
        Assertions.assertEquals("12.8988879", converseOffline.conversion("USD", "CAD", "10", false));
    }

    @Test
    public void testConversionAmount100() {
        Assertions.assertEquals("21.322631", converseOffline.conversion("CNY", "AUD", "100", false));
        Assertions.assertEquals("70.408414", converseOffline.conversion("AUD", "USD", "100", false));
        Assertions.assertEquals("128.988879", converseOffline.conversion("USD", "CAD", "100", false));
    }
}
