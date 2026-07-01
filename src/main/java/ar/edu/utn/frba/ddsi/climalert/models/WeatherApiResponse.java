package ar.edu.utn.frba.ddsi.climalert.models;

import ar.edu.utn.frba.ddsi.climalert.domain.Medicion;
import com.fasterxml.jackson.annotation.JsonProperty;

public record WeatherApiResponse(
        Location location,
        Current current
) {
    public record Location(String name) {}

    public record Current(
            @JsonProperty("temp_c") double temp_c,
            double humidity,
            Condition condition,
            @JsonProperty("wind_kph") double wind_kph
    ) {
        public record Condition(String text) {}
    }

    public Medicion toMedicion() {
        return new Medicion(
                location.name(),
                current.temp_c(),
                current.humidity(),
                current.condition().text(),
                current.wind_kph()
        );
    }
}
