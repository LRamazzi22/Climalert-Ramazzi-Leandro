package ar.edu.utn.frba.ddsi.climalert.services;

import ar.edu.utn.frba.ddsi.climalert.domain.Medicion;

import java.util.Optional;

public interface WeatherService {

    void fetchYGuardar();

    Optional<Medicion> obtenerUltimo();
}
