package it.unicam.cs.ids.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Slf4j
@RestController
public class OSMController {
    @GetMapping("/mappa/nome")
    public ResponseEntity<?> getMapDataByName(@RequestParam String nomeComune) {
        try {
            String url = "https://nominatim.openstreetmap.org/search?q=" + URLEncoder.encode(nomeComune, StandardCharsets.UTF_8) + "&format=json";

            return ResponseEntity.ok(getURL(url));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/mappa/coordinate")
    public ResponseEntity<?> getMapDataByCoordinates(@RequestParam Double latitudine, @RequestParam Double longitudine) {
        try {
            String url = "https://nominatim.openstreetmap.org/reverse.php?" +
                    "lat=" + URLEncoder.encode(String.valueOf(latitudine), StandardCharsets.UTF_8) +
                    "&lon=" + URLEncoder.encode(String.valueOf(longitudine), StandardCharsets.UTF_8) +
                    "&format=jsonv2";

            return ResponseEntity.ok(getURL(url));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private StringBuilder getURL(String url) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        conn.setRequestMethod("GET");

        StringBuilder response = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            String line;
            while ((line = in.readLine()) != null) {
                response.append(line);
            }
        }
        return response;
    }
}
