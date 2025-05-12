import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Conversor {
    private static final HttpClient client = HttpClient.newHttpClient();
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/3614b2f46d822382a843eb6d/pair/";

    public String buscaConversion(String monedaBase, String monedaObjetivo, double cantidad) {
        try {
            String url = API_URL + monedaBase + "/" + monedaObjetivo + "/" + cantidad;
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            String response = client.send(request, HttpResponse.BodyHandlers.ofString()).body();
            JsonObject json = JsonParser.parseString(response).getAsJsonObject();

            return json.get("conversion_result").getAsString();

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error al obtener datos de conversión: " + e.getMessage(), e);
        }
    }
}
