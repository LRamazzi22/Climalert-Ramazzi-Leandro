package ar.edu.utn.frba.ddsi.climalert.models;

public record WeatherDTO(
        String ubicacion,
        double temperaturaCelcius,
        double humedad,
        String condicion,
        double velocidadViento
) {
}
