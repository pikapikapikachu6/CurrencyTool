import model.report.ReportOffline;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class ReportTest {
    @Test
    public void testReport() throws IOException {
        ReportOffline reportOffline = new ReportOffline();
        Assertions.assertEquals("https://i.imgur.com/JGUffIN.png", reportOffline.sendReport("./report.png"));
    }
}
