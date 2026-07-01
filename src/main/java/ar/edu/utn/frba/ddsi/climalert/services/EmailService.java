package ar.edu.utn.frba.ddsi.climalert.services;

import ar.edu.utn.frba.ddsi.climalert.models.WeatherDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${climalert.email.destinatarios}")
    private String[] destinatarios;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void enviarAlerta(WeatherDTO clima) {
        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setTo(destinatarios);
        mensaje.setSubject("⚠️ Alerta climática: condiciones críticas detectadas");
        mensaje.setText(construirCuerpo(clima));
        mailSender.send(mensaje);
        log.info("Alerta climática enviada a {}", String.join(", ", destinatarios));
    }

    private String construirCuerpo(WeatherDTO clima) {
        return """
                Se han detectado condiciones climáticas críticas.

                Detalle del clima actual:
                  Ubicación:          %s
                  Temperatura:        %.1f °C
                  Humedad:            %.0f%%
                  Condición:          %s
                  Velocidad del viento: %.1f km/h

                Por favor, tome las medidas necesarias.
                """.formatted(
                clima.ubicacion(),
                clima.temperaturaCelcius(),
                clima.humedad(),
                clima.condicion(),
                clima.velocidadViento()
        );
    }
}
