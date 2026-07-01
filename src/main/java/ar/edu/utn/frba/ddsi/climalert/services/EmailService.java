package ar.edu.utn.frba.ddsi.climalert.services;

import ar.edu.utn.frba.ddsi.climalert.domain.Medicion;

public interface EmailService {

    void enviarAlerta(Medicion medicion);
}
