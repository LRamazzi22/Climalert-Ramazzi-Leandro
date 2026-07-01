package ar.edu.utn.frba.ddsi.climalert.repositories;

import ar.edu.utn.frba.ddsi.climalert.domain.Medicion;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

@Slf4j
@Repository
public class InMemoryWeatherRepository implements WeatherRepository {

    private final List<Medicion> historial = new CopyOnWriteArrayList<>();

    @Override
    public void guardar(Medicion medicion) {
        this.historial.add(medicion);
        log.info("Clima guardado. Historial: {} mediciones", historial.size());
    }

    @Override
    public Optional<Medicion> obtenerUltimo() {
        if (historial.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(historial.get(historial.size() - 1));
    }

    @Override
    public List<Medicion> obtenerHistorial() {
        return List.copyOf(historial);
    }
}
