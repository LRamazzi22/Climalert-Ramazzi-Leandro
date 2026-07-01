package ar.edu.utn.frba.ddsi.climalert.schedulers;

import ar.edu.utn.frba.ddsi.climalert.services.AlertService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class AlertCheckScheduler {

    private final AlertService alertService;

    public AlertCheckScheduler(AlertService alertService) {
        this.alertService = alertService;
    }

    @Scheduled(fixedRate = 60 * 1000) // Para probar rapido: 15 * 1000
    public void verificarAlertas() {
        alertService.verificarYAlertar();
    }
}
