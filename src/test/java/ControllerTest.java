import controller.Controller;
import model.conversion.ConverseOffline;
import model.conversion.ConverseOnline;
import model.currency.CurrencyOffline;
import model.currency.CurrencyOnline;
import model.report.ReportOffline;
import model.report.ReportOnline;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import javafx.stage.Stage;
import view.View;

import java.io.FileNotFoundException;

import static org.mockito.Mockito.*;

public class ControllerTest {
    @Test
    public void testControllerOnlineOnline() throws FileNotFoundException {
        Stage stage = mock(Stage.class);
        Controller controller = new Controller(stage, "online", "online");
        View view = controller.view;
        Assertions.assertEquals(ConverseOnline.class, view.getConverse().getClass());
        Assertions.assertEquals(CurrencyOnline.class, view.getCurrency().getClass());
        Assertions.assertEquals(ReportOnline.class, view.getReportModel().getClass());
    }

    @Test
    public void testControllerOnlineOffline() throws FileNotFoundException {
        Stage stage = mock(Stage.class);
        Controller controller = new Controller(stage, "online", "offline");
        View view = controller.view;
        Assertions.assertEquals(ConverseOnline.class, view.getConverse().getClass());
        Assertions.assertEquals(CurrencyOnline.class, view.getCurrency().getClass());
        Assertions.assertEquals(ReportOffline.class, view.getReportModel().getClass());
    }

    @Test
    public void testControllerOfflineOnline() throws FileNotFoundException {
        Stage stage = mock(Stage.class);
        Controller controller = new Controller(stage, "offline", "online");
        View view = controller.view;
        Assertions.assertEquals(ConverseOffline.class, view.getConverse().getClass());
        Assertions.assertEquals(CurrencyOffline.class, view.getCurrency().getClass());
        Assertions.assertEquals(ReportOnline.class, view.getReportModel().getClass());
    }

    @Test
    public void testControllerOfflineOffline() throws FileNotFoundException {
        Stage stage = mock(Stage.class);
        Controller controller = new Controller(stage, "offline", "offline");
        View view = controller.view;
        Assertions.assertEquals(ConverseOffline.class, view.getConverse().getClass());
        Assertions.assertEquals(CurrencyOffline.class, view.getCurrency().getClass());
        Assertions.assertEquals(ReportOffline.class, view.getReportModel().getClass());
    }


}
