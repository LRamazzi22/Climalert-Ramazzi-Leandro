package ar.edu.utn.frba.ddsi.climalert.services;

import ar.edu.utn.frba.ddsi.climalert.models.WeatherApiResponse;
import ar.edu.utn.frba.ddsi.climalert.models.WeatherDTO;
import ar.edu.utn.frba.ddsi.climalert.repositories.WeatherRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Optional;

@Service
public class WeatherService {

    private final RestClient restClient;
    private final WeatherRepository weatherRepository;

    @Value("${weatherapi.key}")
    private String apiKey;

    @Value("${weatherapi.location}")
    private String location;

    public WeatherService(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
        this.restClient = RestClient.create("https://api.weatherapi.com/v1");
    }

    public void fetchYGuardar() {
        WeatherApiResponse response = restClient.get()
                .uri("/current.json?key={key}&q={location}&lang=es", apiKey, location)
                .retrieve()
                .body(WeatherApiResponse.class);

        if (response != null) {
            weatherRepository.guardar(response.toDTO());
        }
    }

    public Optional<WeatherDTO> obtenerUltimo() {
        return weatherRepository.obtenerUltimo();
    }
}
