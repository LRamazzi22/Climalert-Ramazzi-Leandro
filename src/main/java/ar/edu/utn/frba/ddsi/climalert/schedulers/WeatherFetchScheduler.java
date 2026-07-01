package ar.edu.utn.frba.ddsi.climalert.schedulers;

import ar.edu.utn.frba.ddsi.climalert.services.WeatherService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class WeatherFetchScheduler {

    private final WeatherService weatherService;

    public WeatherFetchScheduler(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @Scheduled(fixedRate = 5 * 60 * 1000) // Para probar rapido: 10 * 1000
    public void fetchClima() {
        weatherService.fetchYGuardar();
    }
}
