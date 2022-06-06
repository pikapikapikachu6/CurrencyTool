import model.Tool;
import model.conversion.ConverseOnline;
import model.conversion.DataSQL;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class DatabaseTest {
    @Test
    public void testCheckDataExist() {
        DataSQL dataSQL = mock(DataSQL.class);
        when(dataSQL.queryRateExist(anyString(), anyString())).thenReturn("1");
        Tool tool = mock(Tool.class);
        ConverseOnline converseOnline = new ConverseOnline(tool, dataSQL);
        Assertions.assertEquals("1", converseOnline.checkData("AUD", "CNY"));
        verify(dataSQL,times(1)).queryRateExist("AUD","CNY");
    }

    @Test
    public void testCheckDataNotExist() {
        DataSQL dataSQL = mock(DataSQL.class);
        when(dataSQL.queryRateExist(anyString(), anyString())).thenReturn("0");
        Tool tool = mock(Tool.class);
        ConverseOnline converseOnline = new ConverseOnline(tool, dataSQL);
        Assertions.assertEquals("0", converseOnline.checkData("AUD", "CNY"));
        verify(dataSQL,times(1)).queryRateExist("AUD","CNY");
    }

    @Test
    public void testGetDataSuccess() {
        DataSQL dataSQL = mock(DataSQL.class);
        when(dataSQL.queryRate(anyString(), anyString())).thenReturn("4.65268768");
        Tool tool = mock(Tool.class);
        ConverseOnline converseOnline = new ConverseOnline(tool, dataSQL);
        Assertions.assertEquals("AUD to CNY rate is: 4.65268768", converseOnline.getData("AUD", "CNY"));
        verify(dataSQL,times(1)).queryRate("AUD","CNY");
    }

    @Test
    public void testGetDataEmpty() {
        DataSQL dataSQL = mock(DataSQL.class);
        when(dataSQL.queryRate(anyString(), anyString())).thenReturn(null);
        Tool tool = mock(Tool.class);
        ConverseOnline converseOnline = new ConverseOnline(tool, dataSQL);
        Assertions.assertEquals(null, converseOnline.getData("AUD", "CNY"));
        verify(dataSQL,times(1)).queryRate("AUD","CNY");
    }

    @Test
    public void testSaveDataTrue() {
        DataSQL dataSQL = mock(DataSQL.class);
        when(dataSQL.addRate(anyString(), anyString(), anyString())).thenReturn(true);
        Tool tool = mock(Tool.class);
        ConverseOnline converseOnline = new ConverseOnline(tool, dataSQL);
        Assertions.assertTrue(converseOnline.save("AUD", "CNY", "4.65268768"));
        verify(dataSQL,times(1)).addRate("AUD","CNY","4.65268768");
    }

    @Test
    public void testSaveDataFalse() {
        DataSQL dataSQL = mock(DataSQL.class);
        when(dataSQL.addRate(anyString(), anyString(), anyString())).thenReturn(false);
        Tool tool = mock(Tool.class);
        ConverseOnline converseOnline = new ConverseOnline(tool, dataSQL);
        Assertions.assertFalse(converseOnline.save("AUD", "CNY", "4.65268768"));
        verify(dataSQL,times(1)).addRate("AUD","CNY","4.65268768");
    }

    @Test
    public void testConversionCacheOneTrue() {
        DataSQL dataSQL = mock(DataSQL.class);
        when(dataSQL.queryRate(anyString(), anyString())).thenReturn("4.65268768");
        Tool tool = mock(Tool.class);
        ConverseOnline converseOnline = new ConverseOnline(tool, dataSQL);
        Assertions.assertEquals("4.65268768",converseOnline.conversion("AUD", "CNY", "1", true));
        verify(dataSQL,times(1)).queryRate("AUD","CNY");
    }

    @Test
    public void testConversionCacheOneHundredTrue() {
        DataSQL dataSQL = mock(DataSQL.class);
        when(dataSQL.queryRate(anyString(), anyString())).thenReturn("4");
        Tool tool = mock(Tool.class);
        ConverseOnline converseOnline = new ConverseOnline(tool, dataSQL);
        Assertions.assertEquals("400.0",converseOnline.conversion("AUD", "CNY", "100", true));
        verify(dataSQL,times(1)).queryRate("AUD","CNY");
    }
}
