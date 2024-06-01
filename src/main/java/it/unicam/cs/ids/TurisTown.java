package it.unicam.cs.ids;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*String location = "New York";
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

        System.out.println(response.toString());*/

@SpringBootApplication
public class TurisTown {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(TurisTown.class, args);
    }
}

