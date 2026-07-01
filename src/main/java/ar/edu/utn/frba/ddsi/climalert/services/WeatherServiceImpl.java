package ar.edu.utn.frba.ddsi.climalert.services;

import ar.edu.utn.frba.ddsi.climalert.domain.Medicion;
import ar.edu.utn.frba.ddsi.climalert.providers.ProveedorClima;
import ar.edu.utn.frba.ddsi.climalert.repositories.WeatherRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WeatherServiceImpl implements WeatherService {

    private final ProveedorClima proveedorClima;
    private final WeatherRepository weatherRepository;

    public WeatherServiceImpl(ProveedorClima proveedorClima, WeatherRepository weatherRepository) {
        this.proveedorClima = proveedorClima;
        this.weatherRepository = weatherRepository;
    }

    @Override
    public void fetchYGuardar() {
        Medicion medicion = proveedorClima.obtenerClimaActual();

        if (medicion != null) {
            weatherRepository.guardar(medicion);
        }
    }

    @Override
    public Optional<Medicion> obtenerUltimo() {
        return weatherRepository.obtenerUltimo();
    }
}
