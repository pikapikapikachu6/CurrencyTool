package model.report;

import java.io.IOException;

/**
 * Report interface
 */
public interface Report {
    public String transferPageTo64(String path) throws IOException;
    public String sendReport(String path) throws IOException;
}
