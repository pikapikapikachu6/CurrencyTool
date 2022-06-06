package model;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import model.conversion.DataSQL;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Some tool functions
 */
public class Tool {
    public static String report;

    /**
     * Used to: get the full name and abbreviation about all the country from a JSON file
     * @return a map memoried the country full name and abbreviation
     * @throws FileNotFoundException
     */
    public static Map<String, String> readJson() throws FileNotFoundException {
        Map<String, String> countryMap = new HashMap<>();
        File jsonFile = new File("src/main/resource/countries.json");
        FileReader fileReader = new FileReader(jsonFile);
        JsonReader getLocalJsonFile = new JsonReader(fileReader);
        JsonArray countries = new Gson().fromJson(getLocalJsonFile,JsonArray.class);
        for (int i = 0; i < countries.size(); i ++) {
            JsonObject current = countries.get(i).getAsJsonObject();
            String name = current.get("name").getAsString();
            String code = current.get("code").getAsString();
            countryMap.put(code,name);
        }
        return countryMap;
    }

    /**
     * Used to: saved the QR report content
     * @param s the report content
     */
    public static void setReportDate(String s) {
        report = s;
    }

    /**
     * Used to: get the QR report content
     * @return the content
     */
    public static String getReportData() {
        return report;
    }

    /**
     * Helper function: send http request
     * @param url the http url
     * @return the request response
     */
    public JsonObject httpRequest(String url) {
        System.out.println(url);
        try {
            HttpRequest request = HttpRequest.newBuilder(new URI(url))
                    .POST(HttpRequest.BodyPublishers.ofString(""))
                    .build();

            HttpClient client = HttpClient.newBuilder().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response == null || response.statusCode() != 200) {
                String result = "get failed";
                JsonObject res = new JsonObject();
                res.addProperty("result",result);
                return res;
            }
            Gson gson = new Gson();
            JsonObject status = gson.fromJson(response.body(), JsonObject.class).get("response").getAsJsonObject();
            return status;
        } catch (IOException | InterruptedException e) {
            System.out.println("Something went wrong with our request!");
            System.out.println(e.getMessage());
        } catch (URISyntaxException ignored) {
            ignored.printStackTrace();
        }
        return null;
    }

    /**
     * Used to saved about pop-up content
     * @return about pop-up content
     */
    public static List<String> aboutData() {
        List<String> result = new ArrayList<>();
        result.add("NAME: Concurrency Conversion App");
        result.add("Author: Lavender Li (Shutong Li) \n" +
                "References: \n\t Currency Scoop: https://currencyscoop.com/api-documentation" +
                "\n\t Imgur: https://apidocs.imgur.com/ " + "\n\t World Map: https://github.com/controlsfx/controlsfx");
        return result;
    }

    /**
     * Used to saved cache pop-up content
     * @return cache pop-up content
     */
    public static List<String> cacheData() {
        List<String> result = new ArrayList<>();
        result.add("Choose which ways to get data");
        result.add("The cache has this data, do you want to use it? \n Yes: use data in cache " +
                "\n No: use data from API");
        return result;
    }

    /**
     * Used to clean cache
     */
    public static void cleanCache() {
        DataSQL.removeDB();
        DataSQL.createDB();
        DataSQL.setupDB();
    }
}
