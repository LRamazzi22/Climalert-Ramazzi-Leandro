package ar.edu.utn.frba.ddsi.climalert.services;

import ar.edu.utn.frba.ddsi.climalert.domain.Medicion;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    @Value("${climalert.email.destinatarios}")
    private String[] destinatarios;

    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void enviarAlerta(Medicion medicion) {
        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setTo(destinatarios);
        mensaje.setSubject("⚠️ Alerta climática: condiciones críticas detectadas");
        mensaje.setText(construirCuerpo(medicion));
        mailSender.send(mensaje);
        log.info("Alerta climática enviada a {}", String.join(", ", destinatarios));
    }

    private String construirCuerpo(Medicion medicion) {
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
                medicion.ubicacion(),
                medicion.temperaturaCelcius(),
                medicion.humedad(),
                medicion.condicion(),
                medicion.velocidadViento()
        );
    }
}
