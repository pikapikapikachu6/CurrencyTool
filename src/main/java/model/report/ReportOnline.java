package model.report;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * online report type
 * implements Report interface
 */
public class ReportOnline extends ReportAbstract implements Report{
    /**
     * Used to send report to Imgur and get link
     * @param path the content file path
     * @return the URL about the report from Imgur
     * @throws IOException
     */
    @Override
    public String sendReport(String path) throws IOException {
        String result = null;
        String base64 = transferPageTo64(path);

        String url = "https://api.imgur.com/3/upload";
        HttpRequest request = null;
        JsonObject json = new JsonObject();
        json.addProperty("image",base64);
        json.addProperty("type","base64");
        try {
            request = HttpRequest.newBuilder(new URI(url))
                    .POST(HttpRequest.BodyPublishers.ofString(json.toString()))
                    .setHeader("Content-Type", "application/json")
                    .setHeader("Authorization","Client-ID " + System.getenv("IMGUR_API_KEY"))
                    .build();
        } catch (URISyntaxException e) {
            return null;
        }
        HttpClient client = HttpClient.newBuilder().build();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response == null) result = "upload failed";
            else if (response.statusCode() != 200) result = "upload failed";
            else {
                Gson gson = new Gson();
                JsonObject status = gson.fromJson(response.body(), JsonObject.class);
                result = status.get("data").getAsJsonObject().get("link").getAsString();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }
}
