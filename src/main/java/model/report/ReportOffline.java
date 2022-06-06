package model.report;

import java.io.IOException;

/**
 * offline report type
 * implements Report interface
 */
public class ReportOffline extends ReportAbstract implements Report{
    /**
     * Used to send report to Imgur and get link
     * @param path the content file path
     * @return the URL about the report from Imgur
     * @throws IOException
     */
    @Override
    public String sendReport(String path) throws IOException {
        String base64 = transferPageTo64(path);
        String result = "https://i.imgur.com/JGUffIN.png";
        return result;
    }
}
