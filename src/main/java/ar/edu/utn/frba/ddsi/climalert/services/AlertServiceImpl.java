package ar.edu.utn.frba.ddsi.climalert.services;

import org.springframework.stereotype.Service;

@Service
public class AlertServiceImpl implements AlertService {

    private final WeatherService weatherService;
    private final EmailService emailService;

    public AlertServiceImpl(WeatherService weatherService, EmailService emailService) {
        this.weatherService = weatherService;
        this.emailService = emailService;
    }

    @Override
    public void verificarYAlertar() {
        weatherService.obtenerUltimo().ifPresent(medicion -> {
            if (medicion.esCritica()) {
                emailService.enviarAlerta(medicion);
            }
        });
    }
}
