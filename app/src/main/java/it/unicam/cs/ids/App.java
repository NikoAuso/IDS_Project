package it.unicam.cs.ids;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class App {
    public static void main(String[] args) throws Exception {

        String location = "New York";
        String url = "https://nominatim.openstreetmap.org/search?q=" + URLEncoder.encode(location, StandardCharsets.UTF_8) + "&format=json";

        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        conn.setRequestMethod("GET");

        StringBuilder response = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            String line;
            while ((line = in.readLine()) != null) {
                response.append(line);
            }
        }

        System.out.println(response.toString());
    }
}

