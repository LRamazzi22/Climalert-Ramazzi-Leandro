package ar.edu.utn.frba.ddsi.climalert.providers;

import ar.edu.utn.frba.ddsi.climalert.domain.Medicion;
import ar.edu.utn.frba.ddsi.climalert.models.WeatherApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class WeatherApiProvider implements ProveedorClima {

    private final RestClient restClient;

    @Value("${weatherapi.key}")
    private String apiKey;

    @Value("${weatherapi.location}")
    private String location;

    public WeatherApiProvider() {
        this.restClient = RestClient.create("https://api.weatherapi.com/v1");
    }

    @Override
    public Medicion obtenerClimaActual() {
        WeatherApiResponse response = restClient.get()
                .uri("/current.json?key={key}&q={location}&lang=es", apiKey, location)
                .retrieve()
                .body(WeatherApiResponse.class);

        return response != null ? response.toMedicion() : null;
    }
}
