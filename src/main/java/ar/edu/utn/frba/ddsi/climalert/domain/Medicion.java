package ar.edu.utn.frba.ddsi.climalert.domain;

public record Medicion(
        String ubicacion,
        double temperaturaCelcius,
        double humedad,
        String condicion,
        double velocidadViento
) {

    private static final double TEMPERATURA_CRITICA = 35.0;
    private static final double HUMEDAD_CRITICA = 60.0;
    // Para probar sin esperar condiciones reales, reemplazar los valores de arriba por 0 y 0.

    public boolean esCritica() {
        return temperaturaCelcius > TEMPERATURA_CRITICA
                && humedad > HUMEDAD_CRITICA;
    }
}
