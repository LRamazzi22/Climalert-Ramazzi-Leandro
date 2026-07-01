package ar.edu.utn.frba.ddsi.climalert.services;

import ar.edu.utn.frba.ddsi.climalert.models.WeatherDTO;
import org.springframework.stereotype.Service;

@Service
public class AlertService {

    private static final double TEMPERATURA_CRITICA = 35.0;
    private static final double HUMEDAD_CRITICA = 60.0;
    // Para probar sin esperar condiciones reales, reemplazar los valores de arriba por:
    // private static final double TEMPERATURA_CRITICA = 0;
    // private static final double HUMEDAD_CRITICA = 0;

    private final WeatherService weatherService;
    private final EmailService emailService;

    public AlertService(WeatherService weatherService, EmailService emailService) {
        this.weatherService = weatherService;
        this.emailService = emailService;
    }

    public void verificarYAlertar() {
        weatherService.obtenerUltimo().ifPresent(clima -> {
            if (esCritico(clima)) {
                emailService.enviarAlerta(clima);
            }
        });
    }

    private boolean esCritico(WeatherDTO clima) {
        return clima.temperaturaCelcius() > TEMPERATURA_CRITICA
                && clima.humedad() > HUMEDAD_CRITICA;
    }
}
