package ar.edu.utn.frba.ddsi.climalert.repositories;

import ar.edu.utn.frba.ddsi.climalert.domain.Medicion;

import java.util.List;
import java.util.Optional;

public interface WeatherRepository {

    void guardar(Medicion medicion);

    Optional<Medicion> obtenerUltimo();

    List<Medicion> obtenerHistorial();
}
