# Climalert

Servicio de monitoreo climático hecho con Spring Boot. Consulta periódicamente el clima de una ubicación fija en WeatherAPI, guarda las mediciones y, cuando detecta condiciones críticas (temperatura mayor a 35 °C y humedad mayor a 60 %), envía un mail de alerta con el detalle del clima.

Corre como servicio de fondo, sin interfaz. El clima se consulta cada 5 minutos y las alertas se revisan cada 1 minuto.


## Sobre el mail

El envío es por SMTP usando [Mailtrap](https://mailtrap.io/), un sandbox de correo: los mails no se entregan a casillas reales, sino que quedan en un buzón web donde se pueden ver. Por eso los destinatarios pueden ser cualquiera (por defecto, las tres casillas del enunciado). Las credenciales SMTP de Mailtrap están en `application.yaml`.
