package model.report;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;

/**
 * Some same functions in online and offline mode.
 */
public abstract class ReportAbstract {
    /**
     * Used to: transfer file content to base64
     * @param path the file path
     * @return base64 result
     * @throws IOException
     */
    public String transferPageTo64(String path) throws IOException {
        FileInputStream inputStream = null;
        try {
            Base64.Encoder encoder = Base64.getEncoder();
            inputStream = new FileInputStream(path);
            int available = inputStream.available();
            byte[] bytes = new byte[available];
            inputStream.read(bytes);
            String base64Str = encoder.encodeToString(bytes);
            return base64Str;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            inputStream.close();
        }
        return null;
    }
}
