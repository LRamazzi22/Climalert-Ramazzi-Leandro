package ar.edu.utn.frba.ddsi.climalert.repositories;

import ar.edu.utn.frba.ddsi.climalert.models.WeatherDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

@Slf4j
@Repository
public class WeatherRepository {

    private final List<WeatherDTO> historial = new CopyOnWriteArrayList<>();

    public void guardar(WeatherDTO clima) {
        this.historial.add(clima);
        log.info("Clima guardado. Historial: {} mediciones", historial.size());
    }

    public Optional<WeatherDTO> obtenerUltimo() {
        if (historial.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(historial.get(historial.size() - 1));
    }

    public List<WeatherDTO> obtenerHistorial() {
        return List.copyOf(historial);
    }
}
