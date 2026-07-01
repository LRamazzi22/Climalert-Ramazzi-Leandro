package ar.edu.utn.frba.ddsi.climalert.providers;

import ar.edu.utn.frba.ddsi.climalert.domain.Medicion;

public interface ProveedorClima {

    Medicion obtenerClimaActual();
}
